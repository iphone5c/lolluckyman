/**
 * Created by 魏源 on 2015/6/27 0027.
 */
Ext.define('LLManBack.utils.AppContext', {

    invokeSyncService: function (service, op, param, scope, successCallback, fiallCallback) {
        Ext.Ajax.request({
            url: this.getServiceUrl(service, op),
            method: "POST",
            async: true,   //ASYNC 是否异步( TRUE 异步 , FALSE 同步)
            params: param,
            timeout: 6000000,
            scope: this,
            callbackName: name,
            success: function (response, opts) {
                var result = Ext.util.JSON.decode(response.responseText);
                var status= response.getResponseHeader('statusFlag');
                if (status == 1002) {
                    Ext.Msg.alert('提示', '会话超时，请重新登录!',function(){
                        window.location = '/index.jsp';
                    });
                }
                    if (typeof successCallback === 'function') {
                        successCallback.call(scope || window, result.result, response, opts);
                    }


            }, //请求成功的回调函数
            failure: function (response, opts) {
                if (typeof fiallCallback === 'function') {
                    fiallCallback.call(scope || window, {exceptionCode: response.status, exceptionMessage: response.statusText}, response, opts);
                }
            }  // 请求失败的回调函数
        });
    },
    /**
     * 同步调用服务
     * @param service 服务名称
     * @param op 操作名称
     * @param param 参数
     * @returns {*}
     */
    invokeService: function (service, op, param) {
        var result;
        Ext.Ajax.request({
            url: this.getServiceUrl(service, op),
            method: "POST",
            async: false,   //ASYNC 是否异步( TRUE 异步 , FALSE 同步)
            params: param,
            scope: this,
            success: function (response, opts) {
                var status= response.getResponseHeader('statusFlag');
                if(response.responseText==''){
                    var info='{"result":0,"success":true}';
                    result = Ext.util.JSON.decode(info);
                }else{
                    result = Ext.util.JSON.decode(response.responseText);
                }
                result.response = response;
                result.opts = opts;
                if (status == 1002) {
                    result.success = false;
                    result.errorMessage = '没有登录或登录已过期，请重新登录！';
                    Ext.Msg.alert('提示', '会话超时，请重新登录!',function(){
                        window.location = '/index.jsp';
                    });
                }
                if (status == 1003) {
                    result.success = false;
                    result.errorMessage = '你无此操作权限，请联系管理员';
                }
            }, //请求成功的回调函数
            failure: function (response, opts) {
                result = {exceptionCode: response.status, exceptionMessage: response.statusText};
                result.response = response;
                result.opts = opts;
            }  // 请求失败的回调函数
        });
        return result;
    },
    /**
     * 打开一个新窗口
     * @param viewClsName @type String 视图类名称
     * @param config 视图配置
     * @param winConfig 窗口配置
     * @return 打开的窗口对象，可通过innerView属性访问打开的视图
     */
    openWindow: function (viewClsName, config, winConfig) {
        config = config || {};
        winConfig = winConfig || {};

        var newView = Ext.create(viewClsName, config);

        var newWinConfig = {
            title: '窗口',
            height: 400,
            //glyph: 0xf108,
            width: 500,
            modal: true,
            maximizable: true,
            minimizable: false,
            closable: true,
            resizable: true,
            autoScroll:true,
            bodyStyle: 'background-color: #ffffff; padding:0px;'
        };

        Ext.apply(newWinConfig, winConfig);
        Ext.apply(newWinConfig, {items: newView, layout: 'fit', innerView: newView});

        var win = Ext.create('Ext.window.Window', newWinConfig).show();
        newView.meOwner = win;

        var title = '';
        if (typeof newView.getTitle === 'function')
            title = newView.getTitle();
        if ((title == null || title == '') && typeof newView.title !== 'undefined')
            title = newView.title;
        if (title != null && title != '')
            win.setTitle(title);

        return win;
    },
    /**
     * 打开一个新视图
     * @param viewClsName @type String 视图类名称
     * @param config 视图配置
     * @param onlyOne @type boolean 是否仅允许单实例，默认false
     * @param closable @type boolean 是否允许关闭,默认true
     * @return 打开的视图对象
     */
    openView: function (viewClsName, config, onlyOne, closable) {
        config = config || {};
        if (typeof closable === 'undefined')
            closable = true;
        var mainPanel = this.getMainPanel();
        if (mainPanel) {
            var v = this.__findView(viewClsName);
            var newView = false;
            var title = '';
            if (!v || !onlyOne) {
                newView = Ext.create(viewClsName, config);
                if (typeof newView.getTitle === 'function')
                    title = newView.getTitle();
                if ((title == null || title == '') && typeof newView.title !== 'undefined')
                    title = newView.title;
            }
            if (typeof mainPanel.setActiveTab === 'function') {//多文档
                if (newView) {
                    var tabView = {xtype: 'container', title: title, items: [newView], scrollable:true, closable: closable, layout: 'fit' };
                    var tab = mainPanel.add(tabView);
                    tab.innerView = newView;
                    newView.meOwner = tab;
                    mainPanel.setActiveTab(tab);
                    mainPanel.doLayout();
                    return newView;
                }
                else if (v) {
                    mainPanel.setActiveTab(v);
                    mainPanel.doLayout();
                    return v.items.getAt(0);
                }
            }
            else {//单文档
                if (newView) {
                    mainPanel.removeAll(true);
                    mainPanel.add(newView);
                    mainPanel.innerView = newView;
                    newView.meOwner = mainPanel;
                    if (title != null && title != '' && typeof mainPanel.setTitle === 'function')
                        mainPanel.setTitle(title);
                    if(typeof newView.setScrollable === 'function')
                        newView.setScrollable(true);
                    mainPanel.doLayout();
                    return newView;
                }
                else if (v) {
                    return v;
                }
            }
        }
        return false;
    },
    getServiceUrl: function (service, op, param) {
        var url =  service +  op + "?v=" + (new Date()).valueOf();
        var params = this.getServiceParams(param);
        if (params != null)
            url += "&" + encodeURI(params.params);
        return url;
    },
    getServiceParams: function (param) {
        return param ? { params: Ext.util.JSON.encode(param)} : null;
    },
    /**
     * 深度合并对象
     * @param object
     * @param config
     * @returns {*}
     */
    combine: function (object, config) {
        if (object && config && typeof config === 'object') {
            var i;
            for (i in config) {
                if (config[i] === null || config[i] === 'undefined')
                    continue;
                if (typeof config[i] !== 'object') {
                    object[i] = config[i];
                }
                else {
                    if (typeof object[i] === 'undefined')
                        object[i] = {};
                    Ext.combine(object[i], config[i]);
                }
            }
        }
        return object;
    },
    defaultDateFormat:'Y-m-d H:i:s',
    defaultDateParseEmpty:'',
    /**
     * 日期格式化（日期转字符串）
     * @param date 必传
     * @param format
     * @returns {*}
     */
    formatDate:function(date,format){//可只传date
        return Ext.Date.format(date,format||this.defaultDateFormat);
    },
    /**
     * 字符串转日期
     * @param dateString 必传
     * @param format
     * @param emptyText
     * @returns {*|number|string}
     */
    parseDate:function(dateString,format,emptyText){//可只传dateString
        return dateString&&Ext.Date.parse(dateString,format||this.defaultDateFormat)||emptyText||this.defaultDateParseEmpty;
    },
    defaultNumberFormat:'0.00',
    defaultNumberParseEmpty:'0.00',
    /**
     * 数字格式化（数字转字符串）
     * @param num 必传
     * @param format
     * @param emptyText
     * @returns {*|boolean|string}
     */
    formatNumber:function(num,format,emptyText){//可只传num
        return num&&!isNaN(num)&&Ext.util.Format.number(num,format||this.defaultNumberFormat)||emptyText||this.defaultNumberParseEmpty;
    },

    /**
     * 去掉字符串所有逗号
     * @param str 字符串
     * @returns
     */
    clear:function(str){
        str = str.replace(/,/g, "");//取消字符串中出现的所有逗号
        return str;
    }
});