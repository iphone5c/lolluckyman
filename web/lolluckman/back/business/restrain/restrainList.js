/**
 * Created by Restrainistrator on 2016/2/26.
 */
Ext.define('LLManBack.business.restrain.restrainList',{
    extend: 'Ext.grid.Panel',
    // ====入口参数定义===================================================================
    /**
     * 入口参数
     */
    config: {},

    // ====基类属性重写、属性定义==========================================================
    title: '局数信息列表',
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
                        xtype: 'button', text: '修改',  scope: me,
                        handler: function () {
                            var list = me.getSelection();
                            if (list.length != 1)
                                Ext.Msg.alert('提示', '必须并且只能选中一行数据.');
                            else
                                me.showDetailWin(list[0].data.code);
                        }
                    },
                    {
                        xtype: 'button', text: '删除',  scope: me,
                        handler: function () {
                            var list = me.getSelection();
                            if (list.length != 1)
                                Ext.Msg.alert('提示', '必须并且只能选中一行数据.');
                            else
                                me.deleteRestrain(list[0].data.code);
                        }
                    },
                    {
                        xtype: 'button', text: '开启投注',  scope: me,
                        handler: function () {
                            var list = me.getSelection();
                            if (list.length != 1)
                                Ext.Msg.alert('提示', '必须并且只能选中一行数据.');
                            else
                                me.openBetting(list[0].data.code);
                        }
                    },
                    {
                        xtype: 'button', text: '禁止投注',  scope: me,
                        handler: function () {
                            var list = me.getSelection();
                            if (list.length != 1)
                                Ext.Msg.alert('提示', '必须并且只能选中一行数据.');
                            else
                                me.prohibitBetting(list[0].data.code);
                        }
                    }
                ]
            },
            columns: [
                { header: '编号',  dataIndex: 'code',width:153 },
                { header: '比赛名字', dataIndex: 'competitionName',width:120 },
                { header: '局数', dataIndex: 'restrainNum',width:120 },
                { header: '局数状态', dataIndex: 'restrainStatus',width:120 },
                { header: '比赛描述', dataIndex: 'description',width:120 },
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
                {name: 'code', mapping: 'restrain.code'},
                {name: 'competitionName', mapping: 'competition.competitionName'},
                {name: 'restrainNum', mapping: 'restrain.restrainNum'},
                {name: 'restrainStatus', mapping: 'restrain.restrainStatus'},
                {name: 'description', mapping: 'restrain.description'},
                {name: 'createTime', mapping: 'restrain.createTime'}
            ],
            autoLoad: true,
            pageSize:20,
            proxy: {
                url: '/back/restrain/getRestrainPageList',
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

    showDetailWin: function (restrainCode) {
        var win = Ext.appContext.openWindow("LLManBack.business.restrain.forms.restrainDetailForm",{restrainCode: restrainCode}, {width: 340, height: 255});
        win.innerView.on('DataChanged', function (source, param) {
            this.reload();
        }, this);
    },

    /**
     * 删除指定局数信息
     * @param restrainCode 局数信息code
     */
    deleteRestrain:function(restrainCode){
        var result = Ext.appContext.invokeService("/back/restrain","/deleteRestrain", {restrainCode: restrainCode});
        if(result.statusCode!=1000){
            Ext.Msg.alert('操作失败', result.errorMessage);
        }else{
            Ext.Msg.alert('成功', result.result);
            this.reload();
        }
    },

    /**
     * 开启投注
     * @param restrainCode 局数信息code
     */
    openBetting:function(restrainCode){
        var result = Ext.appContext.invokeService("/back/restrain","/openBetting", {restrainCode: restrainCode});
        if(result.statusCode!=1000){
            Ext.Msg.alert('操作失败', result.errorMessage);
        }else{
            Ext.Msg.alert('成功', result.result);
            this.reload();
        }
    },

    /**
     * 禁止投注
     * @param restrainCode 局数信息code
     */
    prohibitBetting:function(restrainCode){
        var result = Ext.appContext.invokeService("/back/restrain","/prohibitBetting", {restrainCode: restrainCode});
        if(result.statusCode!=1000){
            Ext.Msg.alert('操作失败', result.errorMessage);
        }else{
            Ext.Msg.alert('成功', result.result);
            this.reload();
        }
    }
})