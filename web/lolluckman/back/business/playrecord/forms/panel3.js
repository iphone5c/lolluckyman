/**
 * 比分
 * Created by Caven on 2015/2/12.
 */
Ext.define('LLManBack.business.playrecord.forms.panel3', {
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
        //赛事code
        competitionCode:'',
        //父容器
        parent:''
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
    title: '比分',
    border: false,
    bodyBorder: false,
    header:{hidden:true},

    // ====视图构建========================================================================
    initComponent: function () {
        var me = this;
        var data = {};
        if (!Ext.exUtils.isEmpty(me.config.competitionCode)) {
            var result = Ext.appContext.invokeService("/back/competition","/getCompetitionTeamByCode", {competitionCode: me.config.competitionCode});
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
                columnWidths: [150,160, 205, 0],
                tableAttrs: {
                    style: {
                        width: '100%'
                    }
                }
            },
            items: [
                {text:'战队A VS 战队B',islabel: true},
                {text:'比分',islabel: true},
                {text:'赔率',islabel: true},
                {islabel: true },

                { text:data.teamA.chinaName+'('+data.teamA.englishName+') VS '+data.teamB.chinaName+'('+data.teamB.englishName+')',islabel: true},
                {  xtype: 'combobox', name: 'bf1', allowBlank: false, displayField: 'name', valueField: 'value',blankText:'比分不能为空',width:150,editable:false,
                    store:Ext.create('Ext.data.Store', {
                        fields: ['name', 'value'],
                        data : [
                            {"name":"3-0", "value":"3-0"},
                            {"name":"0-3", "value":"0-3"},
                            {"name":"3-1", "value":"3-1"},
                            {"name":"1-3", "value":"1-3"},
                            {"name":"3-2", "value":"3-2"},
                            {"name":"2-3", "value":"2-3"}
                        ]
                    })
                },
                {   xtype:'textfield',name: 'odds1'},
                { },

                { text:data.teamA.chinaName+'('+data.teamA.englishName+') VS '+data.teamB.chinaName+'('+data.teamB.englishName+')',islabel: true},
                {  xtype: 'combobox', name: 'bf2', allowBlank: false, displayField: 'name', valueField: 'value',blankText:'比分不能为空',width:150,editable:false,
                    store:Ext.create('Ext.data.Store', {
                        fields: ['name', 'value'],
                        data : [
                            {"name":"3-0", "value":"3-0"},
                            {"name":"0-3", "value":"0-3"},
                            {"name":"3-1", "value":"3-1"},
                            {"name":"1-3", "value":"1-3"},
                            {"name":"3-2", "value":"3-2"},
                            {"name":"2-3", "value":"2-3"}
                        ]
                    })
                },
                {   xtype:'textfield',name: 'odds2'},
                { },

                { text:data.teamA.chinaName+'('+data.teamA.englishName+') VS '+data.teamB.chinaName+'('+data.teamB.englishName+')',islabel: true},
                {  xtype: 'combobox', name: 'bf3', allowBlank: false, displayField: 'name', valueField: 'value',blankText:'比分不能为空',width:150,editable:false,
                    store:Ext.create('Ext.data.Store', {
                        fields: ['name', 'value'],
                        data : [
                            {"name":"3-0", "value":"3-0"},
                            {"name":"0-3", "value":"0-3"},
                            {"name":"3-1", "value":"3-1"},
                            {"name":"1-3", "value":"1-3"},
                            {"name":"3-2", "value":"3-2"},
                            {"name":"2-3", "value":"2-3"}
                        ]
                    })
                },
                {   xtype:'textfield',name: 'odds3'},
                { },

                { text:data.teamA.chinaName+'('+data.teamA.englishName+') VS '+data.teamB.chinaName+'('+data.teamB.englishName+')',islabel: true},
                {  xtype: 'combobox', name: 'bf4', allowBlank: false, displayField: 'name', valueField: 'value',blankText:'比分不能为空',width:150,editable:false,
                    store:Ext.create('Ext.data.Store', {
                        fields: ['name', 'value'],
                        data : [
                            {"name":"3-0", "value":"3-0"},
                            {"name":"0-3", "value":"0-3"},
                            {"name":"3-1", "value":"3-1"},
                            {"name":"1-3", "value":"1-3"},
                            {"name":"3-2", "value":"3-2"},
                            {"name":"2-3", "value":"2-3"}
                        ]
                    })
                },
                {   xtype:'textfield',name: 'odds4'},
                { },

                { text:data.teamA.chinaName+'('+data.teamA.englishName+') VS '+data.teamB.chinaName+'('+data.teamB.englishName+')',islabel: true},
                {  xtype: 'combobox', name: 'bf5', allowBlank: false, displayField: 'name', valueField: 'value',blankText:'比分不能为空',width:150,editable:false,
                    store:Ext.create('Ext.data.Store', {
                        fields: ['name', 'value'],
                        data : [
                            {"name":"3-0", "value":"3-0"},
                            {"name":"0-3", "value":"0-3"},
                            {"name":"3-1", "value":"3-1"},
                            {"name":"1-3", "value":"1-3"},
                            {"name":"3-2", "value":"3-2"},
                            {"name":"2-3", "value":"2-3"}
                        ]
                    })
                },
                {   xtype:'textfield',name: 'odds5'},
                { },

                { text:data.teamA.chinaName+'('+data.teamA.englishName+') VS '+data.teamB.chinaName+'('+data.teamB.englishName+')',islabel: true},
                {  xtype: 'combobox', name: 'bf6', allowBlank: false, displayField: 'name', valueField: 'value',blankText:'比分不能为空',width:150,editable:false,
                    store:Ext.create('Ext.data.Store', {
                        fields: ['name', 'value'],
                        data : [
                            {"name":"3-0", "value":"3-0"},
                            {"name":"0-3", "value":"0-3"},
                            {"name":"3-1", "value":"3-1"},
                            {"name":"1-3", "value":"1-3"},
                            {"name":"3-2", "value":"3-2"},
                            {"name":"2-3", "value":"2-3"}
                        ]
                    })
                },
                {   xtype:'textfield',name: 'odds6'},
                { }

            ],
            buttons: [
                {
                    text: '确定', scope: this, width: 70, glyph: 0xf00c,
                    handler: function () {
                        var me = this;
                        me.submitData(me.getForm(),data);
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
     * 比分回显数据
     * @param form 提交表单数据
     */
    submitData:function(form,data){
        var me = this;
        if (form.isValid()) {
            var info = form.getFieldValues();
            var tA=data.teamA.code;
            var tB=data.teamB.code;
            var result=
                            tA + "-" + tB + ";" + info.bf1 + ":" +info.odds1+"," +
                            tA + "-" + tB + ";" + info.bf2 + ":" +info.odds2+"," +
                            tA + "-" + tB + ";" + info.bf3 + ":" +info.odds3+"," +
                            tA + "-" + tB + ";" + info.bf4 + ":" +info.odds4+"," +
                            tA + "-" + tB + ";" + info.bf5 + ":" +info.odds5+"," +
                            tA + "-" + tB + ";" + info.bf6 + ":" +info.odds6;

            me.parent.down('#content').setValue(result);
            me.up('window').close();
        }
    }
});