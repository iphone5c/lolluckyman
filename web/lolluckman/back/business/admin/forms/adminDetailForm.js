/**
 * 管理员编辑视图
 * Created by Caven on 2015/2/12.
 */
Ext.define('LLManBack.business.admin.forms.adminDetailForm', {
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
         * ｛String｝ 管理员adminCode为空表示新增，否则就是修改
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
    title: '添加/编辑管理员',
    //bodyPadding: 10,
    border: false,
    bodyBorder: false,
    header:{hidden:true},

    // ====视图构建========================================================================
    initComponent: function () {
        var me = this;
        var data = {};
        var isHidden=false;
        if (!Ext.exUtils.isEmpty(me.config.adminCode)) {
            isHidden=true;
            var result = Ext.appContext.invokeService("/back/admin","/getAdminByCode", {adminCode: me.config.adminCode});
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
                {text:'登录名',islabel: true},
                {
                    xtype:'textfield',name: 'loginName', bind: '{loginName}'
                },
                {},
                {text:'登录密码',islabel: true,hidden:isHidden},
                {
                    xtype:'textfield',inputType:'password',name: 'password',hidden:isHidden
                },
                { hidden:isHidden},
                {text:'确认密码',islabel: true,hidden:isHidden},
                {
                    xtype:'textfield',inputType:'password',name: 'confirmPassword',hidden:isHidden
                },
                {hidden:isHidden}

            ],
            buttons: [
                {
                    text: '确定', scope: this, width: 70, glyph: 0xf00c,
                    handler: function () {
                        var me = this;
                        me.insertOrUpdateAdmin(me.getForm());
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
    insertOrUpdateAdmin:function(form){
        var me = this;
        if (form.isValid()) {
            var info = form.getFieldValues();
            var admin = {
                code:me.config.adminCode,
                loginName:info.loginName,
                password:info.password,
                confirmPassword:info.confirmPassword
            }
            var result;
            if(Ext.exUtils.isEmpty(me.config.adminCode)){
                result = Ext.appContext.invokeService('/back/admin', '/createAdmin', admin);
            }else{
                result = Ext.appContext.invokeService('/back/admin', '/updateAdmin', admin);
            }
            if (result.statusCode != 1000)
                Ext.Msg.alert('操作失败', result.errorMessage);
            else {
                Ext.Msg.alert('成功', "操作成功!");
                me.onDataChangedEvent(me, admin);
                me.up('window').close();
            }
        }
    }
});