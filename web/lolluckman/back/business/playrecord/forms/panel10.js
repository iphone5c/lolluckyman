/**
 * 一峡谷先锋
 * Created by Caven on 2015/2/12.
 */
Ext.define('LLManBack.business.playrecord.forms.panel10', {
    extend: 'Ext.form.Panel',
    requires: [
        'LLManBack.utils.DateTime',
        'LLManBack.utils.DetailLayout'
    ],

    // ====入口参数定义===================================================================
    /**
     * 入口参数
     */
    config: {
        //局数code
        restrainCode:'',
        //父容器
        parent:''
    },

    // ====事件定义========================================================================
    /**
     * 数据改变事件
     * @param source 事件源
     * @param args 事件参数【数据】
     */
    onDataChangedEvent: function (source, args) {
        var me = this;
        me.fireEvent('DataChanged', source, args);
    },

    // ====基类属性重写、属性定义==========================================================
    frame: false,
    title: '小龙数量',
    border: false,
    bodyBorder: false,
    header:{hidden:true},

    // ====视图构建========================================================================
    initComponent: function () {
        var me = this;
        var data = {};
        if (!Ext.exUtils.isEmpty(me.config.restrainCode)) {
            var result = Ext.appContext.invokeService("/back/competition","/getCompetitionTeamByRestrainCode", {restrainCode: me.config.restrainCode});
            if(result.statusCode!=1000){
                Ext.Msg.alert('操作失败', result.errorMessage);
            }else{
                data=result.result;
            }
        }

        Ext.apply(me, {
            defaults: {
                viewModel: {data: data},
                xtype:'label'
            },
            layout:{
                type:'lol-ex-detaillayout',
                columnWidths: [100, 205, 0],
                tableAttrs: {
                    style: {
                        width: '100%'
                    }
                }
            },
            items: [
                {text:'战队',islabel: true},
                {text:'赔率',islabel: true},
                {islabel: true },

                { text:data.teamA.chinaName+'('+data.teamA.englishName+')',islabel: true},
                {   xtype:'textfield',name: 'oddsA'},
                { },

                { text:data.teamB.chinaName+'('+data.teamB.englishName+')',islabel: true},
                {   xtype:'textfield',name: 'oddsB'},
                { },

                { text:'均无',islabel: true},
                {   xtype:'textfield',name: 'oddsQt'},
                { }
            ],
            buttons: [
                {
                    text: '确定', scope: this, width: 70, glyph: 0xf00c,
                    handler: function () {
                        var me = this;
                        me.submitData(me.getForm(),data);
                    }
                },
                {
                    text: '取消', width: 70, scope: this, glyph: 0xf00d,
                    handler: function () {
                        var me = this;
                        me.up('window').close();
                    }
                }
            ]
        });
        me.callParent(arguments);
    },


    //====方法定义=======================================================================

    /**
     * 小龙数量回显数据
     * @param form 提交表单数据
     */
    submitData:function(form,data){
        var me = this;
        if (form.isValid()) {
            var info = form.getFieldValues();
            var tA=data.teamA.code;
            var tB=data.teamB.code;
            var result="{" +
                            tA + ":" +info.oddsA+"," +
                            tB + ":" +info.oddsB+"," +
                            "OTHER:" +info.oddsQt+
                        "}";
            me.parent.down('#content').setValue(result);
            me.up('window').close();
        }
    }
});