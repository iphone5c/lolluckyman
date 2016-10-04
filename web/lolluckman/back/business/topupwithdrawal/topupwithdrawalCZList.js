/**
 * Created by PlayRecordistrator on 2016/2/26.
 */
Ext.define('LLManBack.business.topupwithdrawal.topupwithdrawalCZList',{
    extend: 'Ext.grid.Panel',
    // ====入口参数定义===================================================================
    /**
     * 入口参数
     */
    config: {},

    // ====基类属性重写、属性定义==========================================================
    title: '充值信息列表',
    frame: false,
    border: false,
    header: false,
    columnLines:true,

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
                        xtype: 'button', text: '锁定',  scope: me,
                        handler: function () {
                            var list = me.getSelection();
                            if (list.length != 1)
                                Ext.Msg.alert('提示', '必须并且只能选中一行数据.');
                            else
                                me.locking(list[0].data.code);
                        }
                    },
                    {
                        xtype: 'button', text: '审核',  scope: me,
                        handler: function () {
                            var list = me.getSelection();
                            if (list.length != 1)
                                Ext.Msg.alert('提示', '必须并且只能选中一行数据.');
                            else
                                me.examine(list[0].data.code);
                        }
                    },
                    {
                        xtype: 'button', text: '撤销',  scope: me,
                        handler: function () {
                            var list = me.getSelection();
                            if (list.length != 1)
                                Ext.Msg.alert('提示', '必须并且只能选中一行数据.');
                            else
                                me.revoke(list[0].data.code);
                        }
                    }
                ]
            },
            columns: [
                { header: '编号',  dataIndex: 'code',width:153 },
                { header: '申请金额', dataIndex: 'applyMoney',width:120 },
                { header: '申请人', dataIndex: 'applyAccountCode',width:120 },
                { header: '实际到账金额', dataIndex: 'money',width:120 },
                { header: '申请时间', dataIndex: 'applyTime',width:140 },
                { header: '业务类型', dataIndex: 'businessType',width:120 },
                { header: '交易类型', dataIndex: 'tradeType',width:120 },
                { header: '交易账号', dataIndex: 'tradeNumber',width:120 },
                { header: '处理状态', dataIndex: 'disposalStatus',width:120 },
                { header: '处理时间', dataIndex: 'disposalTime',width:140 },
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
                {name: 'code', mapping: 'code'},
                {name: 'applyMoney', mapping: 'applyMoney'},
                {name: 'applyAccountCode', mapping: 'applyAccountCode'},
                {name: 'money', mapping: 'money'},
                {name: 'applyTime', mapping: 'applyTime'},
                {name: 'businessType', mapping: 'businessType'},
                {name: 'tradeType', mapping: 'tradeType'},
                {name: 'tradeNumber', mapping: 'tradeNumber'},
                {name: 'disposalStatus', mapping: 'disposalStatus'},
                {name: 'disposalTime', mapping: 'disposalTime'}
            ],
            autoLoad: true,
            pageSize:20,
            businessType:'充值',
            proxy: {
                url: '/back/TopUpWithdrawal/getTopUpWithdrawalPageList',
                type: 'ajax',
                extraParams: {pageIndex:0,pageSize:20,businessType:'充值'},
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

    /**
     * 锁定
     * @param topUpwithdrawalCode
     */
    locking:function(topUpwithdrawalCode){
        var result = Ext.appContext.invokeService("/back/TopUpWithdrawal","/locking", {topUpwithdrawalCode: topUpwithdrawalCode});
        if(result.statusCode!=1000){
            Ext.Msg.alert('操作失败', result.errorMessage);
        }else{
            Ext.Msg.alert('成功', result.result);
            this.reload();
        }
    },

    /**
     * 审核
     * @param topUpwithdrawalCode
     */
    examine:function(topUpwithdrawalCode){
        Ext.MessageBox.prompt("确认金额","输入确认金额？",function(btn,txt){
            if(btn=='ok'){
                var result = Ext.appContext.invokeService("/back/TopUpWithdrawal","/examine", {topUpwithdrawalCode: topUpwithdrawalCode,money:txt});
                if(result.statusCode!=1000){
                    Ext.Msg.alert('操作失败', result.errorMessage);
                }else{
                    Ext.Msg.alert('成功', result.result);
                    this.reload();
                }
            }
        });
    },

    /**
     * 撤销
     * @param topUpwithdrawalCode
     */
    revoke:function(topUpwithdrawalCode){
        var result = Ext.appContext.invokeService("/back/TopUpWithdrawal","/revoke", {topUpwithdrawalCode: topUpwithdrawalCode});
        if(result.statusCode!=1000){
            Ext.Msg.alert('操作失败', result.errorMessage);
        }else{
            Ext.Msg.alert('成功', result.result);
            this.reload();
        }
    },

})