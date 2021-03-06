/**
 * Created by Administrator on 2016/2/26.
 */
Ext.define('LLManBack.business.accountassets.accountAssetsList',{
    extend: 'Ext.grid.Panel',
    // ====入口参数定义===================================================================
    /**
     * 入口参数
     */
    config: {},

    // ====基类属性重写、属性定义==========================================================
    title: '账户资产列表',
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
                    {
                        xtype: 'button', text: '新增',  scope: me,
                        handler: function () {
                        }
                    }
                ]
            },
            columns: [
                { header: '会员账号', dataIndex: 'loginAccount',width:120 },
                { header: '真实姓名', dataIndex: 'realName',width:120 },
                { header: '联系电话', dataIndex: 'phone',width:120 },
                { header: '账户状态', dataIndex: 'accountStatus',width:360 },
                { header: '可用竞猜币', dataIndex: 'quizMoney',width:120 },
                { header: '可用抚恤金', dataIndex: 'pensionMoney',width:120 },
                { header: '可用胜利币', dataIndex: 'victoryMoney',width:120 },
                { header: '冻结竞猜币', dataIndex: 'freezeQuizMoney',width:120 },
                { header: '冻结抚恤金', dataIndex: 'freezePensionMoney',width:120 },
                { header: '冻结胜利币', dataIndex: 'freezeVictoryMoney',width:120 },
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
                {name: 'code', mapping: 'accountAssets.code'},
                {name: 'loginAccount', mapping: 'account.loginAccount'},
                {name: 'realName', mapping: 'account.realName'},
                {name: 'phone', mapping: 'account.phone'},
                {name: 'accountStatus', mapping: 'account.accountStatus'},
                {name: 'quizMoney', mapping: 'accountAssets.quizMoney'},
                {name: 'pensionMoney', mapping: 'accountAssets.pensionMoney'},
                {name: 'victoryMoney', mapping: 'accountAssets.victoryMoney'},
                {name: 'freezeQuizMoney', mapping: 'accountAssets.freezeQuizMoney'},
                {name: 'freezePensionMoney', mapping: 'accountAssets.freezePensionMoney'},
                {name: 'freezeVictoryMoney', mapping: 'accountAssets.freezeVictoryMoney'}
            ],
            autoLoad: true,
            pageSize:20,
            proxy: {
                url: '/back/accountassets/getAccountAssetsPageList',
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
    }
})