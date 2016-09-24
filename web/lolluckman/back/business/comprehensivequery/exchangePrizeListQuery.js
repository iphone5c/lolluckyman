/**
 * Created by PlayRecordistrator on 2016/2/26.
 */
Ext.define('LLManBack.business.comprehensivequery.exchangePrizeListQuery',{
    extend: 'Ext.grid.Panel',
    // ====入口参数定义===================================================================
    /**
     * 入口参数
     */
    config: {},

    // ====基类属性重写、属性定义==========================================================
    title: '奖品兑换信息列表',
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
                        xtype: 'button', text: '撤注',  scope: me,
                        handler: function () {
                            var list = me.getSelection();
                            if (list.length != 1)
                                Ext.Msg.alert('提示', '必须并且只能选中一行数据.');
                            else
                                me.deletePlayRecord(list[0].data.code);
                        }
                    }
                ]
            },
            columns: [
                { header: '编号',  dataIndex: 'code',width:153 },
                { header: '兑换产品code', dataIndex: 'prizeCode',width:120 },
                { header: '申请兑换数量', dataIndex: 'applyExchangePrizeNum',width:120 },
                { header: '实际兑换数量', dataIndex: 'exchangePrizeNum',width:120 },
                { header: '消费币种', dataIndex: 'consumptionCurrency',width:140 },
                { header: '申请兑换时间', dataIndex: 'applyExchangeTime',width:120 },
                { header: '兑换状态', dataIndex: 'exchangeStatus',width:120 },
                { header: '处理时间', dataIndex: 'disposalTime',width:120 },
                { header: '充值账号', dataIndex: 'gameAccount',width:120 },
                { header: '申请人code', dataIndex: 'accountCode',width:120 },
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
                {name: 'prizeCode', mapping: 'prizeCode'},
                {name: 'applyExchangePrizeNum', mapping: 'applyExchangePrizeNum'},
                {name: 'exchangePrizeNum', mapping: 'exchangePrizeNum'},
                {name: 'consumptionCurrency', mapping: 'consumptionCurrency'},
                {name: 'applyExchangeTime', mapping: 'applyExchangeTime'},
                {name: 'exchangeStatus', mapping: 'exchangeStatus'},
                {name: 'disposalTime', mapping: 'disposalTime'},
                {name: 'gameAccount', mapping: 'gameAccount'},
                {name: 'accountCode', mapping: 'accountCode'}
            ],
            autoLoad: true,
            pageSize:20,
            proxy: {
                url: '/back/exchangeprize/getExchangePrizePageList',
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

    showDetailWin: function (playRecordCode) {
        var win = Ext.appContext.openWindow("LLManBack.business.playrecord.forms.playRecordDetailForm",{playRecordCode: playRecordCode}, {width: 375, height: 255});
        win.innerView.on('DataChanged', function (source, param) {
            this.reload();
        }, this);
    }

})