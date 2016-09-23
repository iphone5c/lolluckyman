/**
 * 战队编辑视图
 * Created by Caven on 2015/2/12.
 */
Ext.define('LLManBack.business.team.forms.teamDetailForm', {
    extend: 'Ext.form.Panel',
    requires: [
        'LLManBack.utils.DetailLayout'
    ],

    // ====入口参数定义===================================================================
    /**
     * 入口参数
     */
    config: {
        /**
         * ｛String｝ 战队teamCode为空表示新增，否则就是修改
         */
        teamCode: ''
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
    title: '添加/编辑战队',
    //bodyPadding: 10,
    border: false,
    bodyBorder: false,
    header:{hidden:true},

    // ====视图构建========================================================================
    initComponent: function () {
        var me = this;
        var data = {};
        if (!Ext.exUtils.isEmpty(me.config.teamCode)) {
            var result = Ext.appContext.invokeService("/back/team","/getTeamByCode", {teamCode: me.config.teamCode});
            if(result.statusCode!=1000){
                Ext.Msg.alert('操作失败', result.errorMessage);
            }else{
                data=result.result;
            }
        }

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
                {text:'战队中文名',islabel: true},
                {
                    xtype:'textfield',name: 'chinaName', bind: '{chinaName}'
                },
                { },
                {text:'战队外文名',islabel: true},
                {
                    xtype:'textfield',name: 'englishName', bind: '{englishName}'
                },
                { },
                {text:'战队教练',islabel: true},
                {
                    xtype:'textfield',name: 'teamTeacher', bind: '{teamTeacher}'
                },
                { },
                {text:'战队网站',islabel: true},
                {
                    xtype:'textfield',name: 'teamWeb', bind: '{teamWeb}'
                },
                { },
                {text:'战队时间',islabel: true},
                {
                    xtype:'datefield',name: 'createTeamTime', bind: '{createTeamTime}',format:'Y-m-d'
                },
                { },
                {text:'战队简介',islabel: true},
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
                        me.insertOrUpdateTeam(me.getForm());
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
    insertOrUpdateTeam:function(form){
        var me = this;
        if (form.isValid()) {
            var info = form.getFieldValues();
            var team = {
                code:me.config.teamCode,
                chinaName:info.chinaName,
                englishName:info.englishName,
                teamTeacher:info.teamTeacher,
                teamWeb:info.teamWeb,
                createTeamTime:info.createTeamTime,
                description:info.description
            }
            var result;
            if(Ext.exUtils.isEmpty(me.config.teamCode)){
                result = Ext.appContext.invokeService('/back/team', '/createTeam', team);
            }else{
                result = Ext.appContext.invokeService('/back/team', '/updateTeam', team);
            }
            if (result.statusCode != 1000)
                Ext.Msg.alert('操作失败', result.errorMessage);
            else {
                Ext.Msg.alert('成功', "操作成功!");
                me.onDataChangedEvent(me, team);
                me.up('window').close();
            }
        }
    }
});