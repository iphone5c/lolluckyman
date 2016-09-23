/**
 * 赛事信息编辑视图
 * Created by Caven on 2015/2/12.
 */
Ext.define('LLManBack.business.competition.forms.competitionDetailForm', {
    extend: 'Ext.form.Panel',
    requires: [
        'LLManBack.utils.DateTime',
        'LLManBack.utils.DetailLayout'
    ],

    // ====入口参数定义===================================================================
    /**
     * 入口参数
     */
    config: {
        /**
         * ｛String｝ 赛事信息competitionCode为空表示新增，否则就是修改
         */
        competitionCode: ''
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
    title: '添加/编辑赛事信息',
    //bodyPadding: 10,
    border: false,
    bodyBorder: false,
    header:{hidden:true},

    // ====视图构建========================================================================
    initComponent: function () {
        var me = this;
        var data = {};
        if (!Ext.exUtils.isEmpty(me.config.competitionCode)) {
            var result = Ext.appContext.invokeService("/back/competition","/getCompetitionByCode", {competitionCode: me.config.competitionCode});
            if(result.statusCode!=1000){
                Ext.Msg.alert('操作失败', result.errorMessage);
            }else{
                data=result.result;
            }
        }

        //战队
        me.teamCodeResult = Ext.appContext.invokeService("/back/team","/getTeamListToNameValue",{}).result;
        me.teamCodeStore = Ext.create('Ext.data.Store', {
            fields: [],
            data: me.teamCodeResult
        });

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
                {text:'比赛名称',islabel: true},
                {
                    xtype:'textfield',name: 'competitionName', bind: '{competitionName}'
                },
                { },
                {text:'战队A',islabel: true},
                {  xtype: 'combobox', name: 'teamCodeA',bind:'{teamCodeA}',allowBlank: false,displayField: 'name', valueField: 'value',editable:false,
                    store:me.teamCodeStore
                },
                { },
                {text:'战队B',islabel: true},
                {  xtype: 'combobox', name: 'teamCodeB',bind:'{teamCodeB}',allowBlank: false,displayField: 'name', valueField: 'value',editable:false,
                    store:me.teamCodeStore
                },
                { },
                {text:'比赛开始时间',islabel: true},
                {
                    xtype:'datetimefield',name: 'gameStartTime',bind:'{gameStartTime}',format: 'Y-m-d H:i:s',allowBlank: false
                },
                { },

                {text:'赛事信息简介',islabel: true},
                {
                    xtype:'textareafield',name: 'description', bind: '{description}',bodyPadding: 10,store:me
                },
                { }

            ],
            buttons: [
                {
                    text: '确定', scope: this, width: 70, glyph: 0xf00c,
                    handler: function () {
                        var me = this;
                        me.insertOrUpdateCompetition(me.getForm());
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
    insertOrUpdateCompetition:function(form){
        var me = this;
        if (form.isValid()) {
            var info = form.getFieldValues();
            var competition = {
                code:me.config.competitionCode,
                competitionName:info.competitionName,
                teamCodeA:info.teamCodeA,
                teamCodeB:info.teamCodeB,
                gameStartTime:Ext.util.Format.date(info.gameStartTime,'Y-m-d H:i:s'),
                description:info.description
            }
            var result;
            if(Ext.exUtils.isEmpty(me.config.competitionCode)){
                result = Ext.appContext.invokeService('/back/competition', '/createCompetition', competition);
            }else{
                result = Ext.appContext.invokeService('/back/competition', '/updateCompetition', competition);
            }
            if (result.statusCode != 1000)
                Ext.Msg.alert('操作失败', result.errorMessage);
            else {
                Ext.Msg.alert('成功', "操作成功!");
                me.onDataChangedEvent(me, competition);
                me.up('window').close();
            }
        }
    }
});