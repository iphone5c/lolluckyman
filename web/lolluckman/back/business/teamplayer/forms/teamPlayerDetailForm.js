/**
 * 战队成员编辑视图
 * Created by Caven on 2015/2/12.
 */
Ext.define('LLManBack.business.teamplayer.forms.teamPlayerDetailForm', {
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
         * ｛String｝ 战队成员teamPlayerCode为空表示新增，否则就是修改
         */
        teamPlayerCode: ''
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
    title: '添加/编辑战队成员',
    //bodyPadding: 10,
    border: false,
    bodyBorder: false,
    header:{hidden:true},

    // ====视图构建========================================================================
    initComponent: function () {
        var me = this;
        var data = {};
        if (!Ext.exUtils.isEmpty(me.config.teamPlayerCode)) {
            var result = Ext.appContext.invokeService("/back/teamPlayer","/getTeamPlayerByCode", {teamPlayerCode: me.config.teamPlayerCode});
            if(result.statusCode!=1000){
                Ext.Msg.alert('操作失败', result.errorMessage);
            }else{
                data=result.result;
            }
        }

        //所属战队
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
                {text:'战队成员中文名',islabel: true},
                {
                    xtype:'textfield',name: 'chinaName', bind: '{chinaName}'
                },
                { },
                {text:'战队成员外文名',islabel: true},
                {
                    xtype:'textfield',name: 'englishName', bind: '{englishName}'
                },
                { },
                {text:'外号',islabel: true},
                {
                    xtype:'textfield',name: 'alias', bind: '{alias}'
                },
                { },
                {text:'战队位置',islabel: true},
                {
                    xtype:'textfield',name: 'teamLocation', bind: '{teamLocation}'
                },
                { },
                { text: '所属战队', islabel: true},
                {  xtype: 'combobox', name: 'temaCode',bind:'{temaCode}',allowBlank: false,displayField: 'name', valueField: 'value',editable:false,
                    store:me.teamCodeStore
                },
                { },
                {text:'擅长位置',islabel: true},
                {
                    xtype:'textfield',name: 'skilledLocation', bind: '{skilledLocation}'
                },
                { },
                {text:'擅长英雄',islabel: true},
                {
                    xtype:'textfield',name: 'skilledHero', bind: '{skilledHero}'
                },
                { },
                {text:'战队成员简介',islabel: true},
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
                        me.insertOrUpdateTeamPlayer(me.getForm());
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
    insertOrUpdateTeamPlayer:function(form){
        var me = this;
        if (form.isValid()) {
            var info = form.getFieldValues();
            var teamPlayer = {
                code:me.config.teamPlayerCode,
                chinaName:info.chinaName,
                englishName:info.englishName,
                alias:info.alias,
                teamLocation:info.teamLocation,
                temaCode:info.temaCode,
                skilledLocation:info.skilledLocation,
                skilledHero:info.skilledHero,
                description:info.description
            }
            var result;
            if(Ext.exUtils.isEmpty(me.config.teamPlayerCode)){
                result = Ext.appContext.invokeService('/back/teamPlayer', '/createTeamPlayer', teamPlayer);
            }else{
                result = Ext.appContext.invokeService('/back/teamPlayer', '/updateTeamPlayer', teamPlayer);
            }
            if (result.statusCode != 1000)
                Ext.Msg.alert('操作失败', result.errorMessage);
            else {
                Ext.Msg.alert('成功', "操作成功!");
                me.onDataChangedEvent(me, teamPlayer);
                me.up('window').close();
            }
        }
    }
});