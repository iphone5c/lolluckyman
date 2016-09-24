/**
 * Created by 魏源 on 2015/6/26 0026.
 */
Ext.Loader.setConfig({
    enabled: true,
    paths: {
        /*
         * 设置YCUI以及YCTrade命名空间对应的路径
         */
        LLManBack: "lolluckman/back"
    }
});
Ext.application({
    name: 'LLManBackApp',
    launch: function () {
        Ext['appContext'] = Ext.create('LLManBack.utils.AppContext');
        Ext['exUtils'] = Ext.create('LLManBack.utils.ExUtils');

        var auth = Ext.appContext.invokeService('/back/admin', '/loginStatus', {});
        if(auth.statusCode==1000){
            Ext.create('LLManBack.init.Application', {
                renderTo: Ext.getBody()
            });
        }else if(auth.statusCode==1001){
            Ext.create('LLManBack.init.Login', {
                renderTo: Ext.getBody()
            });
        }
    }
});
