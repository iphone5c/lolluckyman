/**
 * 联盟玩法信息编辑视图
 * Created by Caven on 2015/2/12.
 */
Ext.define('LLManBack.business.playrecord.forms.playRecordDetailForm', {
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
         * ｛String｝ 联盟玩法信息playRecordCode为空表示新增，否则就是修改
         */
        playRecordCode: ''
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
    title: '添加/编辑联盟玩法信息',
    //bodyPadding: 10,
    border: false,
    bodyBorder: false,
    header:{hidden:true},

    // ====视图构建========================================================================
    initComponent: function () {
        var me = this;
        var data = {};
        if (!Ext.exUtils.isEmpty(me.config.playRecordCode)) {
            var result = Ext.appContext.invokeService("/back/playRecord","/getPlayRecordByCode", {playRecordCode: me.config.playRecordCode});
            if(result.statusCode!=1000){
                Ext.Msg.alert('操作失败', result.errorMessage);
            }else{
                data=result.result;
            }
        }

        //赛事
        me.competitionResult = Ext.appContext.invokeService("/back/competition","/getCompetitionListToNameValue",{}).result;
        me.competitionStore = Ext.create('Ext.data.Store', {
            fields: [],
            data: me.competitionResult
        });
        //局数
        me.restrainResult = Ext.appContext.invokeService("/back/restrain","/getRestrainListToNameValue",{}).result;
        me.restrainStore = Ext.create('Ext.data.Store', {
            fields: [],
            data: me.restrainResult
        });
        //玩法
        me.playResult = Ext.appContext.invokeService("/back/playRecord","/getPlay",{}).result;
        me.playStore = Ext.create('Ext.data.Store', {
            fields: [],
            data: me.playResult
        });

        Ext.apply(me, {
            defaults: {
                viewModel: {data: data},
                xtype:'label'
            },
            layout:{
                type:'lol-ex-detaillayout',
                columnWidths: [100, 240, 0],
                tableAttrs: {
                    style: {
                        width: '100%'
                    }
                }
            },
            items: [
                {text:'玩法',islabel: true},
                {  xtype: 'combobox',itemId:'play', name: 'play',bind:'{play}',allowBlank: false,displayField: 'name', valueField: 'value',editable:false,width:230,
                    store:me.playStore,
                    listeners : {
                        change:function(field, newValue, oldValue, eOpts){
                            if(newValue=='总局输赢'){
                                me.down('#competition').setDisabled(false);
                                me.down('#restrain').setDisabled(true);
                            }else if(newValue=='比分'){
                                me.down('#competition').setDisabled(false);
                                me.down('#restrain').setDisabled(true);
                            }else{
                                me.down('#competition').setDisabled(true);
                                me.down('#restrain').setDisabled(false);
                            }
                            me.down('#restrain').setValue('');
                            me.down('#competition').setValue('');
                            me.down('#content').setValue('');
                        }
                    }
                },
                { },
                {text:'赛事',islabel: true},
                {  xtype: 'combobox',itemId:'competition', name: 'foreignKey',bind:'{foreignKey}',allowBlank: false,displayField: 'name', valueField: 'value',editable:false,width:230,
                    store:me.competitionStore
                },
                { },
                {text:'局数',islabel: true},
                {  xtype: 'combobox',itemId:'restrain',name: 'foreignKey',bind:'{foreignKey}',allowBlank: false,displayField: 'name', valueField: 'value',editable:false,width:230,
                    store:me.restrainStore
                },
                { },
                {text:'赔率值',islabel: true},
                {
                    xtype:'textareafield',itemId:'content',name: 'content',bind:'{content}',editable:false,width:230,bodyPadding: 10,store:me,
                    listeners : {
                        focus:function(){
                            var play=me.down('#play').getValue()
                            if(play=='总局输赢'){
                                var competition=me.down('#competition').getValue();
                                if(Ext.exUtils.isEmpty(competition)){
                                    Ext.Msg.alert('赔率值配置', '请选择一项赛事进行配置赔率');
                                    return false;
                                }
                                var win = Ext.appContext.openWindow("LLManBack.business.playrecord.forms.panel1",{competitionCode: competition,parent:me}, {width: 340, height: 255});
                            }else if(play=='单局输赢'){
                                var restrainCode=me.down('#restrain').getValue();
                                if(Ext.exUtils.isEmpty(restrainCode)){
                                    Ext.Msg.alert('赔率值配置', '请选择一项局数进行配置赔率');
                                    return false;
                                }
                                var win = Ext.appContext.openWindow("LLManBack.business.playrecord.forms.panel2",{restrainCode: restrainCode,parent:me}, {width: 340, height: 255});
                            }else if(play=='比分'){
                                var competition=me.down('#competition').getValue();
                                if(Ext.exUtils.isEmpty(competition)){
                                    Ext.Msg.alert('赔率值配置', '请选择一项赛事进行配置赔率');
                                    return false;
                                }
                                var win = Ext.appContext.openWindow("LLManBack.business.playrecord.forms.panel3",{competitionCode: competition,parent:me}, {width: 550, height: 320});
                            }else if(play=='一血'){
                                var win = Ext.appContext.openWindow("LLManBack.business.playrecord.forms.panel4",{parent:me}, {width: 340, height: 255});
                            }else if(play=='一塔'){
                                var win = Ext.appContext.openWindow("LLManBack.business.playrecord.forms.panel5",{parent:me}, {width: 340, height: 220});
                            }else if(play=='一小龙'){
                                var restrainCode=me.down('#restrain').getValue();
                                if(Ext.exUtils.isEmpty(restrainCode)){
                                    Ext.Msg.alert('赔率值配置', '请选择一项局数进行配置赔率');
                                    return false;
                                }
                                var win = Ext.appContext.openWindow("LLManBack.business.playrecord.forms.panel6",{restrainCode: restrainCode,parent:me}, {width: 340, height: 220});
                            }else if(play=='第一亚龙属性'){
                                var win = Ext.appContext.openWindow("LLManBack.business.playrecord.forms.panel7",{parent:me}, {width: 340, height: 255});
                            }else if(play=='小龙数量'){
                                var restrainCode=me.down('#restrain').getValue();
                                if(Ext.exUtils.isEmpty(restrainCode)){
                                    Ext.Msg.alert('赔率值配置', '请选择一项局数进行配置赔率');
                                    return false;
                                }
                                var win = Ext.appContext.openWindow("LLManBack.business.playrecord.forms.panel8",{restrainCode: restrainCode,parent:me}, {width: 340, height: 220});
                            }else if(play=='一大龙'){
                                var restrainCode=me.down('#restrain').getValue();
                                if(Ext.exUtils.isEmpty(restrainCode)){
                                    Ext.Msg.alert('赔率值配置', '请选择一项局数进行配置赔率');
                                    return false;
                                }
                                var win = Ext.appContext.openWindow("LLManBack.business.playrecord.forms.panel9",{restrainCode: restrainCode,parent:me}, {width: 340, height: 220});
                            }else if(play=='一峡谷先锋'){
                                var restrainCode=me.down('#restrain').getValue();
                                if(Ext.exUtils.isEmpty(restrainCode)){
                                    Ext.Msg.alert('赔率值配置', '请选择一项局数进行配置赔率');
                                    return false;
                                }
                                var win = Ext.appContext.openWindow("LLManBack.business.playrecord.forms.panel10",{restrainCode: restrainCode,parent:me}, {width: 340, height: 220});
                            }else if(play=='单局四杀'){
                                var restrainCode=me.down('#restrain').getValue();
                                if(Ext.exUtils.isEmpty(restrainCode)){
                                    Ext.Msg.alert('赔率值配置', '请选择一项局数进行配置赔率');
                                    return false;
                                }
                                var win = Ext.appContext.openWindow("LLManBack.business.playrecord.forms.panel11",{restrainCode: restrainCode,parent:me}, {width: 340, height: 220});
                            }else if(play=='单局五杀'){
                                var restrainCode=me.down('#restrain').getValue();
                                if(Ext.exUtils.isEmpty(restrainCode)){
                                    Ext.Msg.alert('赔率值配置', '请选择一项局数进行配置赔率');
                                    return false;
                                }
                                var win = Ext.appContext.openWindow("LLManBack.business.playrecord.forms.panel12",{restrainCode: restrainCode,parent:me}, {width: 340, height: 220});
                            }else if(play=='率先十杀'){
                                var restrainCode=me.down('#restrain').getValue();
                                if(Ext.exUtils.isEmpty(restrainCode)){
                                    Ext.Msg.alert('赔率值配置', '请选择一项局数进行配置赔率');
                                    return false;
                                }
                                var win = Ext.appContext.openWindow("LLManBack.business.playrecord.forms.panel13",{restrainCode: restrainCode,parent:me}, {width: 340, height: 220});
                            }else if(play=='单局超神'){
                                var restrainCode=me.down('#restrain').getValue();
                                if(Ext.exUtils.isEmpty(restrainCode)){
                                    Ext.Msg.alert('赔率值配置', '请选择一项局数进行配置赔率');
                                    return false;
                                }
                                var win = Ext.appContext.openWindow("LLManBack.business.playrecord.forms.panel14",{restrainCode: restrainCode,parent:me}, {width: 340, height: 220});
                            }else if(play=='一水晶'){
                                var win = Ext.appContext.openWindow("LLManBack.business.playrecord.forms.panel15",{parent:me}, {width: 340, height: 220});
                            }else if(play=='单方人头数单双'){
                                var restrainCode=me.down('#restrain').getValue();
                                if(Ext.exUtils.isEmpty(restrainCode)){
                                    Ext.Msg.alert('赔率值配置', '请选择一项局数进行配置赔率');
                                    return false;
                                }
                                var win = Ext.appContext.openWindow("LLManBack.business.playrecord.forms.panel16",{restrainCode: restrainCode,parent:me}, {width: 500, height: 250});
                            }else if(play=='总人头数单双'){
                                var win = Ext.appContext.openWindow("LLManBack.business.playrecord.forms.panel17",{parent:me}, {width: 340, height: 220});
                            }
                        }
                    }
                },
                { }
            ],
            buttons: [
                {
                    text: '确定', scope: this, width: 70, glyph: 0xf00c,
                    handler: function () {
                        var me = this;
                        me.insertOrUpdatePlayRecord(me.getForm());
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
    insertOrUpdatePlayRecord:function(form){
        var me = this;
        if (form.isValid()) {
            var info = form.getFieldValues();
            var playRecord = {
                code:me.config.playRecordCode,
                foreignKey:info.foreignKey,
                play:info.play,
                content:info.content
            }
            var result;
            if(Ext.exUtils.isEmpty(me.config.playRecordCode)){
                result = Ext.appContext.invokeService('/back/playRecord', '/createPlayRecord', playRecord);
            }else{
                result = Ext.appContext.invokeService('/back/playRecord', '/updatePlayRecord', playRecord);
            }
            if (result.statusCode != 1000)
                Ext.Msg.alert('操作失败', result.errorMessage);
            else {
                Ext.Msg.alert('成功', "操作成功!");
                me.onDataChangedEvent(me, playRecord);
                me.up('window').close();
            }
        }
    }
});