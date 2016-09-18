/**
 * Created by 魏源 on 2015/9/24 0024.
 */
/**
 * 批量文件上传通用组件
 * Created by Caven on 2015/2/12.
 */
Ext.define('LLManBack.utils.FileUpload', {
    extend: 'Ext.grid.Panel',
    requires: [
        'LLManBack.utils.PluploadButton'
    ],

    // ====入口参数定义===================================================================
    /**
     * 入口参数
     * extensions 定义选择上传文件的类型，默认为所有文件类型
     */
    config: {
        extensions:'',
        parent:''
    },

    // ====基类属性重写、属性定义==========================================================
    title: '文件上传',
    frame: false,
    border: false,
    header: false,
    autoScroll:true,

    // ====视图构建========================================================================
    initComponent: function () {
        var me = this;
        var uploader;
        var files;
        var store = Ext.create('Ext.data.Store', {
            storeId:'simpsonsStore',
            fields:['fileId','fileName', 'fileType', 'fileSize', 'fileDesc','fileUploadTime', 'progress', 'status','filePath'],
            data:{'items':[]},
            proxy: {
                type: 'memory',
                reader: {
                    type: 'json',
                    root: 'items'
                }
            }
        });
        Ext.apply(me, {
            store: store,
            columnLines: true,
            selModel: {
                selType: 'checkboxmodel'
            },
            tbar: {
                xtype: 'toolbar', scope: me,
                items: [
                    {
                        xtype: 'pluploadbutton', text: '添加文件', glyph: 0xf067, scope: me,
                        pluploadConfig:{
                            url:'/back/business/publicController/upload',
                            flash_swf_url:'resources/lib/extjs/plupload-2.1.8/Moxie.swf',
                            multipart_params:{cachePath:'/upload/product/cache'},
                            filters:{
                                mime_types:[
                                    {
                                        title:'文件',
                                        extensions:'*'
                                    }
                                ]
                            },
                            init:{
                                FilesAdded: function(up, files) {
                                    plupload.each(files, function(file) {
                                        me.store.add({'fileId':file.id, 'fileName':file.name,  'fileType':file.type,'fileSize':plupload.formatSize(file.size),'fileDesc':'','progress':file.percent+'%','fileUploadTime':'','filePath':'','status':file.status});
                                    });
                                    me.uploader=up;
                                    me.files=files;
                                },
                                UploadProgress: function(up, file) {
                                    var records=me.store.data;
                                    for(var i=0;i<records.length;i++){
                                        if(file.id==records.getAt(i).data.fileId){
                                            records.getAt(i).set('progress',file.percent+'%');
                                            records.getAt(i).set('status',file.status);
                                            records.getAt(i).commit();
                                        }
                                    }
                                },
                                FileUploaded:function(up,file,response){
                                    var result=eval("("+response.response+")");
                                    var records=me.store.data;
                                    for(var i=0;i<records.length;i++){
                                        if(file.id==records.getAt(i).data.fileId){
                                            records.getAt(i).set('progress',file.percent+'%');
                                            records.getAt(i).set('status',file.status);
                                            records.getAt(i).set('fileUploadTime',result.result.file.fileUploadTime);
                                            records.getAt(i).set('filePath',result.result.file.filePath);
                                            records.getAt(i).set('fileName',result.result.file.fileName);
                                            records.getAt(i).commit();
                                            return true;
                                        }
                                    }
                                },
                                UploadComplete:function(up,file){
                                    Ext.Msg.alert('操作成功', '上传完毕.');
                                },
                                Error: function(up, err) {
                                    Ext.Msg.alert('操作失败', '上传失败，失败原因：'+err.message);
                                }
                            }
                        }
                    },
                    {
                        xtype: 'button', text: '上传', glyph: 0xf067, scope: me,
                        handler: function () {
                            me.uploader.start();
                        }
                    },
                    {
                        xtype: 'button', text: '停止上传', glyph: 0xf068, scope: me,
                        handler: function () {
                            me.uploader.stop();
                        }
                    },
                    {
                        xtype: 'button', text: '删除', glyph: 0xf068, scope: me,
                        handler: function () {
                            var list = me.getSelection();
                            if (list.length == 0) {
                                Ext.Msg.alert('提示', '没有选中任何数据.');
                            } else {
                                Ext.Msg.confirm('提示', '你确定要删除选中的上传文件吗？', function (btn) {
                                    if (btn === 'yes') {
                                        var records=me.store.data;
                                        Ext.each(list, function (item, index) {
                                            if(item.status==2){
                                                Ext.Msg.alert('提示', '此文件('+item.name+')正在上传，不能进行删除.');
                                                return true;
                                            }
                                            for(var i=0;i<records.length;i++){
                                                if(item.id==records.getAt(i).id){
                                                    me.store.removeAt(i);
                                                    me.uploader.removeFile(me.uploader.getFile(item.data.fileId));
                                                    return true;
                                                }
                                            }
                                        });
                                    }
                                }, this);

                            }
                        }
                    }
                ]
            },
            fbar: {
                items: [
                    {
                        xtype: 'button',
                        text: '确认',
                        glyph: 0xf067,
                        scope: me,
                        handler:function(){
                            var records=me.store.data;
                            for(var i=0;i<records.length;i++){
                                if(records.getAt(i).data.status==5){
                                    me.config.parent.down('#fjsc').store.add({'fileId':records.getAt(i).data.fileId, 'fileName':records.getAt(i).data.fileName,  'fileType':records.getAt(i).data.fileType,'fileSize':records.getAt(i).data.fileSize,'fileDesc':records.getAt(i).data.fileDesc,'fileUploadTime':records.getAt(i).data.fileUploadTime,'filePath':records.getAt(i).data.filePath,'fileUploadCode':''});
                                }
                            }
                            me.up('window').close();
                        }
                    },
                    {
                        xtype: 'button', text: '关闭', glyph: 0xf068, scope: me,
                        handler:function(){
                            me.up('window').close();
                        }
                    }
                ]
            },
            columns: [
                { header: '文件名称',  dataIndex: 'fileName',width:200 },
                { header: '文件类型', dataIndex: 'fileType' },
                { header: '大小', dataIndex: 'fileSize' },
                { header: '文件说明', dataIndex: 'fileDesc' },
                { header: '进度', dataIndex: 'progress' },
                { header: '上传时间', dataIndex: 'fileUploadTime' },
                { header: '状态', dataIndex: 'status',flex: 1,
                    renderer:function(val){
                        return me.getFileStatus(val);
                    }
                }
            ]
        });
        me.callParent(arguments);
    },
    //====方法定义=======================================================================
    getFileStatus:function(status){
        var info='';
        switch(status){//-------二次修改------
            case 1 :
                info= '<span style="color:#559fff;">  未上传</span>';
                break;
            case 2 :
                info='<span style="color:brown;">  正在上传</span>';
                break;
            case 4 :
                info='<span style="color:red;">  上传失败</span>';
                break;
            case 5 :
                info='<span style="color:green;">  上传成功</span>';
                break;
        }
        return info;
    }
});