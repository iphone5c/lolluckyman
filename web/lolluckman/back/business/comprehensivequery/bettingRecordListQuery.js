/**
 * Created by PlayRecordistrator on 2016/2/26.
 */
Ext.define('LLManBack.business.comprehensivequery.bettingRecordListQuery',{
    extend: 'Ext.grid.Panel',
    // ====入口参数定义===================================================================
    /**
     * 入口参数
     */
    config: {},

    // ====基类属性重写、属性定义==========================================================
    title: '投注信息列表',
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
            columns: [
                { header: '编号',  dataIndex: 'code',width:153 },
                { header: '投注人编号', dataIndex: 'accountCode',width:120 },
                { header: '投注玩法编号', dataIndex: 'playRecordCode',width:120 },
                { header: '投注竞猜币数量', dataIndex: 'quizMoney',width:120 },
                { header: '投注时间', dataIndex: 'bettingRecordTime',width:140 },
                { header: '投注结果', dataIndex: 'bettingRecordResult',width:120 },
                { header: '投注状态', dataIndex: 'bettingRecordStatus',width:120 },
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
                {name: 'accountCode', mapping: 'accountCode'},
                {name: 'playRecordCode', mapping: 'playRecordCode'},
                {name: 'quizMoney', mapping: 'quizMoney'},
                {name: 'bettingRecordTime', mapping: 'bettingRecordTime'},
                {name: 'bettingRecordResult', mapping: 'bettingRecordResult'},
                {name: 'bettingRecordStatus', mapping: 'bettingRecordStatus'}
            ],
            autoLoad: true,
            pageSize:20,
            proxy: {
                url: '/back/bettingrecord/getBettingRecordPageList',
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