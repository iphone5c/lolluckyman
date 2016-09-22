/**
 * 奖品编辑视图
 * Created by Caven on 2015/2/12.
 */
Ext.define('LLManBack.business.prize.forms.prizeDetailForm', {
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
         * ｛String｝ 奖品prizeCode为空表示新增，否则就是修改
         */
        prizeCode: ''
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
    title: '添加/编辑奖品',
    //bodyPadding: 10,
    border: false,
    bodyBorder: false,
    header:{hidden:true},

    // ====视图构建========================================================================
    initComponent: function () {
        var me = this;
        var data = {};
        if (!Ext.exUtils.isEmpty(me.config.prizeCode)) {
            var result = Ext.appContext.invokeService("/back/prize","/getPrizeByCode", {prizeCode: me.config.prizeCode});
            if(result.statusCode!=1000){
                Ext.Msg.alert('操作失败', result.errorMessage);
            }else{
                data=result.result;
            }
        }

        //奖品类型
        me.prizeTypeResult = Ext.appContext.invokeService("/back/prize","/getPrizeType",{}).result;
        me.prizeTypeStore = Ext.create('Ext.data.Store', {
            fields: [],
            data: me.prizeTypeResult
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
                {text:'奖品名称',islabel: true},
                {
                    xtype:'textfield',name: 'prizeName', bind: '{prizeName}'
                },
                { },
                { text: '奖品类型', islabel: true},
                {  xtype: 'combobox', name: 'prizeType',bind:'{prizeType}',allowBlank: false,displayField: 'name', valueField: 'value',editable:false,
                    store:me.prizeTypeStore
                },
                { },
                {text:'奖品描述',islabel: true},
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
                        me.insertOrUpdatePrize(me.getForm());
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
    insertOrUpdatePrize:function(form){
        var me = this;
        if (form.isValid()) {
            var info = form.getFieldValues();
            var prize = {
                code:me.config.prizeCode,
                prizeName:info.prizeName,
                prizeType:info.prizeType,
                description:info.description
            }
            var result;
            if(Ext.exUtils.isEmpty(me.config.prizeCode)){
                result = Ext.appContext.invokeService('/back/prize', '/createPrize', prize);
            }else{
                result = Ext.appContext.invokeService('/back/prize', '/updatePrize', prize);
            }
            if (result.statusCode != 1000)
                Ext.Msg.alert('操作失败', result.errorMessage);
            else {
                Ext.Msg.alert('成功', "操作成功!");
                me.onDataChangedEvent(me, prize);
                me.up('window').close();
            }
        }
    }
});