/**
 * Created by Administrator on 2016/2/26.
 */
Ext.define('LLManBack.init.menuPanel', {
    extend: 'Ext.tree.Panel',
    rootVisible:false,  // 显示root节点
    requires: [],
    initComponent: function () {
        /*
         * 代码注意逻辑分离，不要揉到一堆
         * 一个函数内的代码行数尽量精简，同时逻辑功能尽量独立
         */
        var store = Ext.create('Ext.data.TreeStore', {
            root: {
                expanded: true,
                children: [
                    { text: "系统管理", expanded: true, children: [
                        { text: "管理员",module:'LLManBack.business.admin.adminList', leaf: true },
                        { text: "系统参数配置",module:'LLManBack.business.systemparams.systemParamsList', leaf: true}
                    ] },
                    { text: "账户管理", expanded: false, children: [
                        { text: "玩家账户管理",module:'LLManBack.business.account.accountList', leaf: true },
                        { text: "账户资产管理",module:'LLManBack.business.accountassets.accountAssetsList', leaf: true}
                    ] },
                    { text: "奖品管理", expanded: false, children: [
                        { text: "奖品管理",module:'LLManBack.business.prize.prizeList', leaf: true }
                    ] },
                    { text: "业务管理", expanded: false, children: [
                        { text: "充值管理",module:'YC_CORESYSTEM_ACCOUNT.accountList', leaf: true },
                        { text: "提现管理",module:'YC_CORESYSTEM_ACCOUNT.accountList', leaf: true },
                        { text: "奖品兑换管理",module:'YC_CORESYSTEM_ACCOUNT.accountList', leaf: true},
                        { text: "玩家投注管理",module:'YC_CORESYSTEM_ACCOUNT.accountList', leaf: true}
                    ] },
                    { text: "比赛管理", expanded: false, children: [
                        { text: "战队成员管理",module:'LLManBack.business.teamplayer.teamPlayerList', leaf: true },
                        { text: "战队管理",module:'LLManBack.business.team.teamList', leaf: true},
                        { text: "赛事管理",module:'LLManBack.business.competition.competitionList', leaf: true},
                        { text: "比赛局数管理",module:'LLManBack.business.restrain.restrainList', leaf: true},
                        { text: "联盟玩法管理",module:'YC_CORESYSTEM_ACCOUNT.accountList', leaf: true}
                    ] },
                    { text: "综合查询", expanded: false, children: [
                        { text: "充值提现查询",module:'YC_CORESYSTEM_ACCOUNT.accountList', leaf: true },
                        { text: "奖品兑换记录查询",module:'YC_CORESYSTEM_ACCOUNT.accountList', leaf: true},
                        { text: "投注记录查询",module:'YC_CORESYSTEM_ACCOUNT.accountList', leaf: true},
                        { text: "比赛结果查询",module:'YC_CORESYSTEM_ACCOUNT.accountList', leaf: true}
                    ] },
                ]
            }
        });
//        var treeStroe=this.createStore();
        var me=this;
        Ext.applyIf(me, {
            title: 'Simple Tree',
            width: 200,
            height: 150,
            store: store,
            rootVisible: false,
            listeners: {
                itemclick: function (myTree, record) {
                    if (record.data.module != '' && record.data.url != '') {
                        /*
                         * 触发一个自定义事件
                         * 这个事件在myApp.js中
                         */
                        me.fireEvent('menuSelected', {
                            moduleId: record.data.module
                        });
                    }
                },
                scope: this
            }

        });
        this.callParent(arguments);
    }

//    createStore: function () {
//        var treeStore=Ext.appContext.invokeService('/back/commons/commons','/getMenuList',{});
//        if(treeStore.statusCode!=1000){
//            Ext.Msg.alert('操作失败',treeStore.errorMessage);
//            return;
//        }
//        var store = Ext.create('Ext.data.TreeStore', {
//            root: {
//                expanded: true,
//                children: treeStore.result
//            }
//        });
//        return store;
//    }
});