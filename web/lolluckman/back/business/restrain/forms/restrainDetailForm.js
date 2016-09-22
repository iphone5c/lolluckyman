/**
 * 局数信息编辑视图
 * Created by Caven on 2015/2/12.
 */
Ext.define('LLManBack.business.restrain.forms.restrainDetailForm', {
    extend: 'Ext.form.Panel',
    requires: [
        'LLManBack.utils.DetailLayout'
    ],

    // ====入口参数定义===================================================================
    /**
     * 入口参数
     */
    config: {
        /**
         * ｛String｝ 局数信息restrainCode为空表示新增，否则就是修改
         */
        restrainCode: ''
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
    title: '添加/编辑局数信息',
    //bodyPadding: 10,
    border: false,
    bodyBorder: false,
    header:{hidden:true},

    // ====视图构建========================================================================
    initComponent: function () {
        var me = this;
        var data = {};
        if (!Ext.exUtils.isEmpty(me.config.restrainCode)) {
            var result = Ext.appContext.invokeService("/back/restrain","/getRestrainByCode", {restrainCode: me.config.restrainCode});
            if(result.statusCode!=1000){
                Ext.Msg.alert('操作失败', result.errorMessage);
            }else{
                data=result.result;
            }
        }

        //赛事
        me.competitionCodeResult = Ext.appContext.invokeService("/back/competition","/getCompetitionListToNameValue",{}).result;
        me.competitionCodeStore = Ext.create('Ext.data.Store', {
            fields: [],
            data: me.competitionCodeResult
        });

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
                {text:'比赛',islabel: true},
                {  xtype: 'combobox', name: 'competitionCode',bind:'{competitionCode}',allowBlank: false,displayField: 'name', valueField: 'value',editable:false,
                    store:me.competitionCodeStore
                },
                { },
                {text:'局数顺序',islabel: true},
                {
                    xtype:'textfield',name: 'restrainNum', bind: '{restrainNum}'
                },
                { },
                {text:'局数信息简介',islabel: true},
                {
                    xtype:'textfield',name: 'description', bind: '{description}'
                },
                { }

            ],
            buttons: [
                {
                    text: '确定', scope: this, width: 70, glyph: 0xf00c,
                    handler: function () {
                        var me = this;
                        me.insertOrUpdateRestrain(me.getForm());
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
     * 新增或者修改用户
     * @param form 提交表单数据
     */
    insertOrUpdateRestrain:function(form){
        var me = this;
        if (form.isValid()) {
            var info = form.getFieldValues();
            var restrain = {
                code:me.config.restrainCode,
                competitionCode:info.competitionCode,
                restrainNum:info.restrainNum,
                description:info.description
            }
            var result;
            if(Ext.exUtils.isEmpty(me.config.restrainCode)){
                result = Ext.appContext.invokeService('/back/restrain', '/createRestrain', restrain);
            }else{
                result = Ext.appContext.invokeService('/back/restrain', '/updateRestrain', restrain);
            }
            if (result.statusCode != 1000)
                Ext.Msg.alert('操作失败', result.errorMessage);
            else {
                Ext.Msg.alert('成功', "操作成功!");
                me.onDataChangedEvent(me, restrain);
                me.up('window').close();
            }
        }
    }
});