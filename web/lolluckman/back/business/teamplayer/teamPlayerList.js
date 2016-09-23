/**
 * Created by TeamPlayeristrator on 2016/2/26.
 */
Ext.define('LLManBack.business.teamplayer.teamPlayerList',{
    extend: 'Ext.grid.Panel',
    // ====入口参数定义===================================================================
    /**
     * 入口参数
     */
    config: {},

    // ====基类属性重写、属性定义==========================================================
    title: '战队成员列表',
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
                                me.deleteTeamPlayer(list[0].data.code);
                        }
                    }
                ]
            },
            columns: [
                { header: '编号',  dataIndex: 'code',width:153 },
                { header: '中文名字', dataIndex: 'chinaName',width:120 },
                { header: '外文名字', dataIndex: 'englishName',width:360 },
                { header: '外号', dataIndex: 'alias',width:360 },
                { header: '战队位置', dataIndex: 'teamLocation',width:360 },
                { header: '所属战队', dataIndex: 'teamChinaName',width:360 },
                { header: '擅长位置', dataIndex: 'skilledLocation',width:360 },
                { header: '擅长英雄', dataIndex: 'skilledHero',width:360 },
                { header: '成员简介', dataIndex: 'description',width:360 },
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
                {name: 'code', mapping: 'teamPlayer.code'},
                {name: 'chinaName', mapping: 'teamPlayer.chinaName'},
                {name: 'englishName', mapping: 'teamPlayer.englishName'},
                {name: 'alias', mapping: 'teamPlayer.alias'},
                {name: 'teamLocation', mapping: 'teamPlayer.teamLocation'},
                {name: 'teamChinaName', mapping: 'team.chinaName'},
                {name: 'skilledLocation', mapping: 'teamPlayer.skilledLocation'},
                {name: 'skilledHero', mapping: 'teamPlayer.skilledHero'},
                {name: 'description', mapping: 'teamPlayer.description'},
                {name: 'createTime', mapping: 'teamPlayer.createTime'}
            ],
            autoLoad: true,
            pageSize:20,
            proxy: {
                url: '/back/teamPlayer/getTeamPlayerPageList',
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

    showDetailWin: function (teamPlayerCode) {
        var win = Ext.appContext.openWindow("LLManBack.business.teamplayer.forms.teamPlayerDetailForm",{teamPlayerCode: teamPlayerCode}, {width: 340, height: 400});
        win.innerView.on('DataChanged', function (source, param) {
            this.reload();
        }, this);
    },

    /**
     * 删除指定战队成员
     * @param teamPlayerCode 战队成员code
     */
    deleteTeamPlayer:function(teamPlayerCode){
        var result = Ext.appContext.invokeService("/back/teamPlayer","/deleteTeamPlayer", {teamPlayerCode: teamPlayerCode});
        if(result.statusCode!=1000){
            Ext.Msg.alert('操作失败', result.errorMessage);
        }else{
            Ext.Msg.alert('成功', result.result);
            this.reload();
        }
    }
})