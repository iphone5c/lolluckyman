/**
 * 总人头数单双
 * Created by Caven on 2015/2/12.
 */
Ext.define('LLManBack.business.playrecord.forms.panel17', {
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
    title: '总人头数单双',
    border: false,
    bodyBorder: false,
    header:{hidden:true},

    // ====视图构建========================================================================
    initComponent: function () {
        var me = this;
        Ext.apply(me, {
            defaults: {
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
                {text:'人头总数',islabel: true},
                {text:'赔率',islabel: true},
                {islabel: true },

                { text:'单数',islabel: true},
                {   xtype:'textfield',name: 'oddsSingular'},
                { },

                { text:'双数',islabel: true},
                {   xtype:'textfield',name: 'oddsDual'},
                { }

            ],
            buttons: [
                {
                    text: '确定', scope: this, width: 70, glyph: 0xf00c,
                    handler: function () {
                        var me = this;
                        me.submitData(me.getForm());
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
     * 总人头数单双回显数据
     * @param form 提交表单数据
     */
    submitData:function(form){
        var me = this;
        if (form.isValid()) {
            var info = form.getFieldValues();
            var result=
                            "SINGULAR:" +info.oddsSingular+"," +
                            "DUAL:" +info.oddsDual;

            me.parent.down('#content').setValue(result);
            me.up('window').close();
        }
    }
});