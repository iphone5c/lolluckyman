/**
 * 管理员编辑视图
 * Created by Caven on 2015/2/12.
 */
Ext.define('LLManBack.business.admin.forms.updateAdminPasswordForm', {
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
         * ｛String｝ 需要修改密码的管理员code
         */
        adminCode: ''
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
    title: '修改密码',
    //bodyPadding: 10,
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
                {text:'旧密码',islabel: true},
                {
                    xtype:'textfield',inputType:'password',name: 'oldPassword'
                },
                { },

                {text:'新密码',islabel: true},
                {
                    xtype:'textfield',inputType:'password',name: 'newPassword'
                },
                { },

                {text:'确认密码',islabel: true},
                {
                    xtype:'textfield',inputType:'password',name: 'confirmPassword'
                },
                { }

            ],
            buttons: [
                {
                    text: '确定', scope: this, width: 70, glyph: 0xf00c,
                    handler: function () {
                        var me = this;
                        me.updateAdminPassword(me.getForm());
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
     * 修改密码
     * @param form 提交表单数据
     */
    updateAdminPassword:function(form){
        var me = this;
        if (form.isValid()) {
            var info = form.getFieldValues();
            var adminpassword = {
                adminCode:me.config.adminCode,
                oldPassword:info.oldPassword,
                newPassword:info.newPassword,
                confirmPassword:info.confirmPassword
            }
            var result = Ext.appContext.invokeService('/back/admin', '/updateAdminPassword', adminpassword);
            if (result.statusCode != 1000)
                Ext.Msg.alert('操作失败', result.errorMessage);
            else {
                Ext.Msg.alert('成功', result.result);
                me.onDataChangedEvent(me, adminpassword);
                me.up('window').close();
            }
        }
    }
});