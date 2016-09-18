/**
 * Created by 魏源 on 2015/9/23 0023.
 */
Ext.define('LLManBack.utils.PluploadButton', {
    extend: 'Ext.Button',
    alias: ['widget.pluploadbutton'],
    requires: ['LLManBack.utils.Plupload'],
    constructor: function (config) {
        var me = this;
        me.callParent(arguments);
        me.on('render', function (btn, eOpts) {
            btn.uploader = Ext.create('LLManBack.utils.Plupload', {
                browseButton: btn,
                pluploadConfig: eOpts.pluploadConfig
            });
        }, me, { pluploadConfig: config.pluploadConfig });
        me.on('destroy', function (btn, eOpts) {
            if (btn.uploader) {
                btn.uploader.uploader.destroy();
            }
        })
    }
 });