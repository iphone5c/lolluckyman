/**
 * 显示一段文本
 * Created by Caven on 2015/2/12.
 */
Ext.define('LLManBack.utils.TextLabel', {
    extend: 'Ext.Component',
    xtype: 'yc-ex-textlabel',
    requires: [ ],

    // ====入口参数定义===================================================================
    /**
     * 入口参数
     */
    config: {
        /**
         * {String} 按钮文本
         */
        text: '',
        /**
         * {function(text,source)} 对呈现前的值进行处理
         */
        renderer:null
    },

    // ====事件定义========================================================================
    // ====基类属性重写、属性定义==========================================================
    defaultBindProperty: 'text',
    autoEl: 'span',
    maskOnDisable: false,

    // ====构造方法========================================================================
    //constructor: function (config) {
    //    var me = this;
    //    config = config || {};
    //    Ext.applyIf(config, me.config);
    //    me.callParent(arguments);
    //},

    // ====视图构建========================================================================
    initComponent: function () {
        var me = this;
        me.callParent(arguments);
    },


    //====方法定义=======================================================================
    getElConfig: function () {
        var me = this;
        me.displayText = me.text;
        if (typeof me.renderer === 'function') {
            me.displayText = me.renderer.call(me.scope || me, me.text, me);
        }
        me.html = me.displayText;
        return Ext.apply(me.callParent(), {
            htmlFor: me.forId || ''
        });
    },
    setText: function (text, encode) {
        var me = this;
        me.text = text;
        me.displayText = me.text;
        if (typeof me.renderer === 'function') {
            me.displayText = me.renderer.call(me.scope || me, me.text, me);
        }
        if (me.rendered) {
            me.el.dom.innerHTML = me.displayText;
            me.updateLayout();
        }
        return me;
    }


});