Ext.define('LLManBack.init.Application',{
    extend:'Ext.container.Viewport',
    layout: 'border',
    auth:'',
    /**
     * 由于左侧区域与右侧区域内部都需要显示内容需要创建出来
     */
    funiValidation: {

    //---------------只允许输入数字和英文--
        funiEnglishNumberEnabled:true,
    funiEnglishNumber: function(inputValue){
    var   c   =   /^[a-zA-Z0-9]+$/;
    if   (c.test(inputValue))
        return   true;
    else
        return   false;
},
        funiEnglishNumberText: '只允许输入数字和英文',
        funiEnglishNumberMask: /.*/i,
        //---------------只允许输入数字--
        funiNumberEnabled:true,
        funiNumber: function(inputValue){
            var   c   =   /^[0-9]*$/;
            if   (c.test(inputValue))
                return   true;
            else
                return   false;
        },
        funiNumberText: '只允许输入数字',
        funiNumberMask: /.*/i,

        //---------------只允许输入数字，逗号，小数点--
        funiNumberMoneyEnabled:true,
        funiNumberMoney: function(inputValue){
            var   c   =   /^[0-9\,\.]*$/;
            if   (c.test(inputValue))
                return   true;
            else
                return   false;
        },
        funiNumberMoneyText: '只允许输入数字,逗号和小数点',
        funiNumberMoneyMask: /.*/i,

        //---------------只允许输入数字，小数点--
        funiNumberMoney1Enabled:true,
        funiNumberMoney1: function(inputValue){
            var   c   =   /^[0-9\.]*$/;
            if   (c.test(inputValue))
                return   true;
            else
                return   false;
        },
        funiNumberMoney1Text: '只允许输入数字,逗号和小数点',
        funiNumberMoney1Mask: /.*/i,

        //---------------只允许输入英文验证和数字-funiCheckEngCharacter--
        funiCheckEngCharacterEnabled:true,
        funiCheckEngCharacter: function(inputValue){
            var   c   =   /^(?!_)[a-zA-Z0-9_]+$/;
            if   (c.test(inputValue))
                return   true;
            else
                return   false;
        },
        funiCheckEngCharacterText: '只允许输入英文、数字、下划线,不能以下划线开头',
        funiCheckEngCharacterMask: /.*/i,

        //----------------输入长度验证（最长40）：vtype:'funiText'----------------
        funiTextEnabled: true,
        funiText: function (inputValue) {
            return !this.funiTextEnabled || (inputValue.length && inputValue.length >= 0 && inputValue.length < 200);
        },
        funiTextText: '输入最大长度为200',
        funiTextMask: /.*/i,

        //----------------密码验证（6-20）：vtype:'funiText'----------------
        funiPwdEnabled: true,
        funiPwd: function (inputValue) {
            return !this.funiPwdEnabled || (inputValue.length && inputValue.length >= 6 && inputValue.length < 20&&/^\S*$/g.test(inputValue));
        },
        funiPwdText: '请输入6~20位且不包含空格的密码',
        funiPwdMask: /.*/i,


        //----------------输入长度验证（最长10000）：vtype:'funiText'----------------
        funiTextAreaEnabled: true,
        funiTextArea: function (inputValue) {
            return !this.funiTextAreaEnabled || (inputValue.length && inputValue.length >= 0 && inputValue.length <= 100000);
        },
        funiTextAreaText: '输入最大长度为100000',
        funiTextAreaMask: /.*/i,

        //----------------名称验证：vtype:'funiName'----------------
        funiNameEnabled: true,
        funiName: function (inputValue) {
            var validateReg = /^[\w\u4e00-\u9fa5]{0,40}$/;
            return !this.funiNameEnabled || validateReg.test(inputValue);
        },
        funiNameText: '输入不超过40位的数字、字母或汉字',
        funiNameMask: /.*/i,

        //----------------人名验证：vtype:'funiManName'----------------
        funiManNameEnabled: true,
        funiManName: function (inputValue) {
            var validateReg = /^[a-zA-Z\u4e00-\u9fa5]+$/;
            return !this.funiManNameEnabled || inputValue.length >= 0 && inputValue.length <=20 && validateReg.test(inputValue);
        },
        funiManNameText: '输入不超过20位的字母或汉字',
        funiManNameMask: /.*/i,
        //---------------只允许输入英文验证和数字-funiCheckEngCharacter--
        funiCheckEngCharacterEnabled:true,
        funiCheckEngCharacter: function(inputValue){
            var   c   =   /^(?!_)[a-zA-Z0-9_]+$/;
            if   (c.test(inputValue))
                return   true;
            else
                return   false;
        },
        funiCheckEngCharacterText: '只允许输入英文、数字、下划线,不能以下划线开头',
        funiCheckEngCharacterMask: /.*/i,
        //---------------只允许输入不超过50位的英文、汉字、下划线,不能以下划线开头-funiCheckCharacter--
        funiCheckCharacterEnabled:true,
        funiCheckCharacter: function(inputValue){
            var   cc   =   /^(?!_)[a-zA-Z\u4e00-\u9fa5_]{0,40}$/;
            return !this.funiCheckCharacterEnabled || cc.test(inputValue);
        },
        funiCheckCharacterText: '只允许输入不超过50位的英文、汉字、下划线,不能以下划线开头',
        funiCheckCharacterMask: /.*/i,

        //----------------电话验证(座机或手机)：vtype:'funiPhone'----------------
        funiPhoneEnabled: true,
        funiPhone: function (inputValue) {
            var isTel = function (s) {
                var patrn = /^((\+?86)|(\(\+86\)))?\d{3,4}-\d{7,8}(-\d{3,4})?$/
                return patrn.exec(s);
            };

            var isMobile = function (value) {
                var validateReg = /^((\+?86)|(\(\+86\)))?1\d{10}$/;
                return validateReg.test(value);
            };

            return !this.funiPhoneEnabled || (isTel(inputValue) || isMobile(inputValue));
        },
        funiPhoneText: '请输入正确的电话号码',
        funiPhoneMask: /.*/i,





        //----------------传真号码(传真号码)：vtype:'funiPhone'----------------
        funiFaxEnabled: true,
        funiFax: function (inputValue) {
            var isTel = function (s) {
                var patrn = /^((\+?86)|(\(\+86\)))?\d{3,4}-\d{7,8}(-\d{3,4})?$/
                return patrn.exec(s);
            };

            var isMobile = function (value) {
                var validateReg = /^((\+?86)|(\(\+86\)))?1\d{10}$/;
                return validateReg.test(value);
            };

            return !this.funiPhoneEnabled || (isTel(inputValue) || isMobile(inputValue));

        },
        funiFaxText: '请输入正确的传真号码',
        funiFaxMask: /.*/i,

        //----------------手机验证：vtype:'funiMobile'----------------
        funiMobileEnabled: true,
        funiMobile: function (inputValue) {
            var validateReg = /^((\+?86)|(\(\+86\)))?1\d{10}$/;
            return !this.funiMobileEnabled || validateReg.test(inputValue);
        },
        funiMobileText: '请输入正确的手机号码',
        funiMobileMask: /.*/i,

        //----------------邮编验证：vtype:'funiECode'----------------
        funiECodeEnabled: true,
        funiECode: function (inputValue) {
            var validateReg = /^[0-9]{6}$/g;
            return !this.funiECodeEnabled || validateReg.test(inputValue);
        },
        funiECodeText: '请输入正确的邮编(6位数字)',
        funiECodeMask: /.*/i,

        //----------------证件号验证：vtype:'funiIdentNum'----------------
        funiIdentNumEnabled: true,
        funiIdentNum: function (inputValue) {
            var validateReg = /^[\w]{0,40}$/;
            return !this.funiIdentNumEnabled || validateReg.test(inputValue);
        },
        funiIdentNumText: '证件号只允许输入不超过40位的数字和字母',
        funiIdentNumMask: /.*/i,

        //----------------正数且小数点后最多2位：vtype:'funiPNumber'----------------
        funiCommonNumberEnabled: true,
        funiCommonNumber: function (inputValue) {
            var checkScale = function (num) {
                if (isNaN(num)) {
                    return false;
                }
                return /^\d+\.?\d{0,2}$/g.test(num);
            }
            return !this.funiCommonNumberEnabled || (inputValue>=0 && checkScale(inputValue));
        },
        funiCommonNumberText: '请输入不小于0，小数点后最多2位的数字',
        funiCommonNumberMask: /^\d+\.?\d{0,2}$/g,

        //----------------正数且小数点后最多2位：vtype:'funiPNumber'----------------
        funiPNumberEnabled: true,
        funiPNumber: function (inputValue) {
            var checkScale = function (num) {
                if (isNaN(num)) {
                    return false;
                }
                return /^\d+\.?\d{0,2}$/g.test(num);
            }
            return !this.funiPNumberEnabled || (inputValue >0 && checkScale(inputValue));
        },
        funiPNumberText: '请输入大于0，小数点后最多2位的数字',
        funiPNumberMask: /^\d+\.?\d{0,2}$/g
    },

    initComponent:function(){
        //拿到当前登录用户
        var user = Ext.appContext.invokeService('/back/business/mainApp', '/authentication', {}).result;
        //  1.添加验证规则
        Ext.apply(Ext.form.field.VTypes, this.funiValidation);
        //  2.设置验证样式
        //Ext.form.Field.prototype.msgTarget = 'qtip';
        Ext.tip.Tip.prototype.cls = 'yc-tip-style';

        Ext.view.AbstractView.prototype.loadingText = '读取中...';
        // numberfield 全局默认验证(具体的地方，可以另行指定vtype来覆盖默认验证规则)
        Ext.form.field.Number.prototype.vtype='funiCommonNumber';
        // textfield 全局默认验证(具体的地方，可以另行指定vtype来覆盖默认验证规则)
        Ext.form.field.Text.prototype.vtype='funiText';
        //自定义样式（提示框）
        Ext.window.MessageBox.prototype.bodyCls = 'funi-msg-box';
        var me = this;
        //左侧导航区域
        this.navigatorPanel = Ext.create('YCBack.business.menu.NavigatorPanel', {  // 原本可以写在外面，涉及作用域写 进来
            title: '功能模块',
            region: 'west',
            width: 250,
            split: true,
            collapsible: true,
            border:false,
            autoScroll:false,
            cls:'home-navigator-panel'
        });

        this.workspacePanel = Ext.create('YCBack.business.menu.WorkspacePanel', {
            region: 'center',
            '$panel-header-line-height':35,
            border:false,
            cls:'home-workspace-panel',
            listeners: {
                render: function (source, eOpts) {
                    if(user.deptId=='cfaa7aa7-f69f-4bf9-aec4-7a122609a513'){
                        this.openModule('YCBack.pagehome.Index',{
                            closable : false,
                            /*
                             * 这里就是上面说的，用itemId来替代id
                             */
                            itemId:'YCBack.pagehome.Index',
                            workSpacePanel:this
                        });
                    }
                }
            }
        });
        this.navigatorPanel.on('moduleSelected',function(param){
            this.workspacePanel.openModule(param.moduleId,{
                closable : true,
                /*
                 * 这里就是上面说的，用itemId来替代id
                 */
                itemId:param.moduleId,
                workSpacePanel:this.workspacePanel
            });
        },this);
        Ext.applyIf(me, {
            layout : "border",
            items:[
                {
                    xtype:'container',
                    title : "logo",
                    region : "north",
                    height : 100,
                    style:'text-align:center',
                    header : false,
                    collapsible : true,
                    cls:'home-banner-x',
                    layout:{type:'border'},
                    items:[
                        {region:'center',xtype:'container'},
                        {
                            region:'east',xtype:'container',layout:'vbox',width: 300,
                            items:[
                                {
                                    xtype: 'component', padding: '3 0 0 0 ',
                                    cls:'login-info',
                                    html: ('当前登录用户：<span id="luName">'+user.fullName||'') +'</span><br/>'
                                            +'登录名：'+user.userName+'<br/>'
                                            +Ext.util.Format.date(new Date(), 'Y-m-d H:i')
                                    , height: 70 },
                                {
                                    xtype: 'container', layout: 'hbox', margin: '5 0 0 0',
                                    items: [
                                        {
                                            xtype: 'button', text: '退出系统', scope: this,
                                            handler: function () {
                                                Ext.Msg.confirm('提示', '你确定要退出系统吗？', function (btn) {
                                                    if (btn === 'yes') {
                                                        var result = Ext.appContext.invokeService('/back/business/mainApp', '/logout',{});
                                                        window.location.href = 'index.jsp';
                                                    }
                                                }, this);
                                            }
                                        },
                                        {
                                            xtype: 'button', text: '个人信息', margin: '0 0 0 10',
                                            menu: [
                                                { text: '修改个人信息' ,
                                                    listeners:{
                                                        click:function( menu, item, e, eOpts ){
                                                            Ext.appContext.openWindow("YCBack.business.user.forms.UserDetailForm",
                                                                {userId: user.userId,
                                                                    listeners:{
                                                                        DataChanged:function(panel,userParam){
                                                                            var fullName = userParam.user.fullName;
                                                                            Ext.dom.Query.selectNode('#luName').innerHTML = fullName;
                                                                        }
                                                                    }
                                                                }, {width: 300, height: 215});
                                                        }
                                                    }
                                                },
                                                { text: '修改密码' ,
                                                    listeners:{
                                                        click:function( menu, item, e, eOpts ){
                                                            Ext.appContext.openWindow("YCBack.business.user.forms.UserChangePwdForm",
                                                                {userId: user.userId}, {width: 300, height: 245});
                                                        }
                                                    }
                                                }
                                            ]
                                        }
                                    ]
                                }
                            ]
                        }
                    ]
                },
                this.navigatorPanel,
                this.workspacePanel
            ]
        });
        me.callParent(arguments);

        /**
         * 服务端发生错误响应码的跳转
         */
        Ext.Ajax.on('requestcomplete',function(conn,response,options) {
            if(!response||typeof response.getResponseHeader!='function'){
                return true;
            }
            var status=response.getResponseHeader('statusFlag');

           if(status=="1002"){
//                会话超时
                Ext.Msg.alert('提示', '会话超时，请重新登录!',function(){
                    me.destroy();
                    Ext.create('YCBack.init.Login', {
                        title: '四川金融资产交易所 - 业务管理系统',
                        renderTo: Ext.getBody()
                    });
//                    window.location = '/index.jsp';
                });
            }else if(status=="1003"){
               Ext.Msg.alert('操作失败', '你无此操作权限，请联系管理员');
               return false;
           }
        },Window);
    }
})