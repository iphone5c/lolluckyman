/**
 * Created by PlayRecordistrator on 2016/2/26.
 */
Ext.define('LLManBack.business.playrecord.playRecordList',{
    extend: 'Ext.grid.Panel',
    // ====入口参数定义===================================================================
    /**
     * 入口参数
     */
    config: {},

    // ====基类属性重写、属性定义==========================================================
    title: '联盟玩法信息列表',
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
                        xtype: 'button', text: '新增',  scope: me,
                        handler: function () {
                            me.showDetailWin();
                        }
                    },
                    {
                        xtype: 'button', text: '删除',  scope: me,
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
                { header: '外键', dataIndex: 'foreignKey',width:120 },
                { header: '玩法', dataIndex: 'play',width:120 },
                { header: '赔率详情', dataIndex: 'content',width:120 },
                { header: '获胜方', dataIndex: 'success',width:140 },
                { header: '结果描述', dataIndex: 'resultDesc',width:120 },
                { header: '状态', dataIndex: 'playRecordStatus',width:120 },
                { header: '创建时间', dataIndex: 'createTime',width:140 },
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
                {name: 'foreignKey', mapping: 'foreignKey'},
                {name: 'play', mapping: 'play'},
                {name: 'content', mapping: 'content'},
                {name: 'success', mapping: 'success'},
                {name: 'resultDesc', mapping: 'resultDesc'},
                {name: 'playRecordStatus', mapping: 'playRecordStatus'},
                {name: 'createTime', mapping: 'createTime'}
            ],
            autoLoad: true,
            pageSize:20,
            proxy: {
                url: '/back/playRecord/getPlayRecordPageList',
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
    },

    /**
     * 删除指定联盟玩法信息
     * @param playRecordCode 联盟玩法信息code
     */
    deletePlayRecord:function(playRecordCode){
        var result = Ext.appContext.invokeService("/back/playRecord","/deletePlayRecord", {playRecordCode: playRecordCode});
        if(result.statusCode!=1000){
            Ext.Msg.alert('操作失败', result.errorMessage);
        }else{
            Ext.Msg.alert('成功', result.result);
            this.reload();
        }
    }

})