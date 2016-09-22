/**
 * Created by Administrator on 2016/2/26.
 */
Ext.define('LLManBack.business.account.accountList',{
    extend: 'Ext.grid.Panel',
    // ====入口参数定义===================================================================
    /**
     * 入口参数
     */
    config: {},

    // ====基类属性重写、属性定义==========================================================
    title: '账户列表',
    frame: false,
    border: false,
    header: false,
    columnLines:true,
    autoScroll:true,

    // ====初始化定义==========================================================
    initComponent: function () {
        var me=this;
        var store = me.createStore();

        Ext.applyIf(me, {                             //如果指定对象不存在相同的属性，将配置的属性拷贝到指定对象
            store: store,
            selModel: {
                selType: 'checkboxmodel'
            },
            tbar:{
                xtype: 'toolbar', scope: me,
                items:[
//                    {
//                        xtype: 'button', text: '新增',  scope: me,
//                        handler: function () {
//                            me.showDetailWin();
//                        }
//                    },
                    {
                        xtype: 'button', text: '禁用',  scope: me,
                        handler: function () {
                            var list = me.getSelection();
                            if (list.length != 1)
                                Ext.Msg.alert('提示', '必须并且只能选中一行数据.');
                            else
                                me.disableAccount(list[0].data.code);
                        }
                    },
                    {
                        xtype: 'button', text: '解除禁用',  scope: me,
                        handler: function () {
                            var list = me.getSelection();
                            if (list.length != 1)
                                Ext.Msg.alert('提示', '必须并且只能选中一行数据.');
                            else
                                me.removeDisableAccount(list[0].data.code);
                        }
                    }
                ]
            },
            columns: [
                { header: '编号',  dataIndex: 'code',width:153 },
                { header: '会员账号', dataIndex: 'loginAccount',width:120 },
                { header: '真实姓名', dataIndex: 'realName',width:120 },
                { header: '联系电话', dataIndex: 'phone',width:120 },
                { header: 'E-mail', dataIndex: 'email',width:120 },
                { header: 'QQ', dataIndex: 'qq',width:360 },
                { header: '密码问题', dataIndex: 'problem',width:360 },
                { header: '密码答案', dataIndex: 'passwordAnswer',width:360 },
                { header: '账户状态', dataIndex: 'accountStatus',width:360 },
                { header: '注册时间', dataIndex: 'createTime',width:140 },
                { header: '描述', dataIndex: 'description',width:360 },
                { flex: 1 }
            ],
            dockedItems: [
                {
                    xtype: 'pagingtoolbar', dock: 'bottom', displayInfo: true,
                    store: store,
                    listeners: {
                        beforechange: function (source, pageNum) {
                            var me = this;
                            this.reload({pageIndex: pageNum - 1});
                            return false;
                        },
                        scope: me
                    }
                }
            ],
            height: 200,
            width: 400
        });
        this.callParent(arguments);
    },

    // ====基类方法定义==========================================================
    reload: function (params) {
        var me = this;
        Ext.exUtils.combine(me.store.proxy.extraParams, params);
        me.store.reload();
        me.store.currentPage = me.store.proxy.extraParams.pageIndex + 1;
    },

    createStore:function(){
        var store=Ext.create('Ext.data.Store', {
            fields:[
                {name: 'code', mapping: 'account.code'},
                {name: 'loginAccount', mapping: 'account.loginAccount'},
                {name: 'realName', mapping: 'account.realName'},
                {name: 'phone', mapping: 'account.phone'},
                {name: 'email', mapping: 'account.email'},
                {name: 'qq', mapping: 'account.qq'},
                {name: 'problem', mapping: 'problem'},
                {name: 'passwordAnswer', mapping: 'account.passwordAnswer'},
                {name: 'accountStatus', mapping: 'account.accountStatus'},
                {name: 'description', mapping: 'account.description'},
                {name: 'createTime', mapping: 'account.createTime'}
            ],
            autoLoad: true,
            pageSize:20,
            proxy: {
                url: '/back/account/getAccountPageList',
                type: 'ajax',
                extraParams: {pageIndex:0,pageSize:20},
                reader: {
                    type: 'json',
                    rootProperty: 'result.list',
                    totalProperty: 'result.totalSize',
                    messageProperty: 'errorMessage',
                    successProperty: 'success'
                }
            }
        });
        return store;
    },

    showDetailWin: function (adminCode) {
        var heigth=Ext.exUtils.isEmpty(adminCode)? 255:185;
        var win = Ext.appContext.openWindow("LLManBack.business.admin.forms.adminDetailForm",{adminCode: adminCode}, {width: 300, height: heigth});
        win.innerView.on('DataChanged', function (source, param) {
            this.reload();
        }, this);
    },

    /**
     * 禁用
     * @param adminCode 用户code
     */
    disableAccount:function(accountCode){
        var result = Ext.appContext.invokeService("/back/account","/disableAccount", {code: accountCode});
        if(result.statusCode!=1000){
            Ext.Msg.alert('操作失败', result.errorMessage);
        }else{
            Ext.Msg.alert('成功', result.result);
            this.reload();
        }
    },

    /**
     * 解除禁用
     * @param adminCode 用户code
     */
    removeDisableAccount:function(accountCode){
        var result = Ext.appContext.invokeService("/back/account","/removeDisableAccount", {code: accountCode});
        if(result.statusCode!=1000){
            Ext.Msg.alert('操作失败', result.errorMessage);
        }else{
            Ext.Msg.alert('成功', result.result);
            this.reload();
        }
    }

})