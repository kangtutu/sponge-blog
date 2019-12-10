//============================= 分类及标签页面博客列表相关 START ==========================

//点击更多按钮页面样式
function nextButton(objId,currentPageNumber,lastPage){
    //对查看更多按钮进行重新赋值操作
    $('#nextButton').html('');
    var btn = '<button type="button" class="btn btn-sm btn-outline-dark ';
    if(lastPage){
        btn += 'disabled';
    }
    btn += ' m-next-btn"';
    btn += 'data-obj-id="'+objId+'"';
    btn += 'data-current-page="'+currentPageNumber+'"';
    if(lastPage){
        btn += '>';
        btn += '到底了';
    }else{
        btn += 'onclick="appendBlog(this.getAttribute(\'data-obj-id\'),this.getAttribute(\'data-current-page\'))">';
        btn += '查看更多';
    }
    btn += '</button>';
    $('#nextButton').html(btn);
}

//页面文章展示数据模板
function blogTemplate(b){
    var imageUrl = b.imageUrl;
    var title = b.title;
    var content = b.content;
    var blogId = b.blogId;
    var author = b.author;
    var time = new Date(b.creationTime).Format("yyyy-MM-dd");
    var readNumber = b.readingQuantity;
    var linkNumber = b.likeNumInt;
    var labelName = b.labelName;
    var typeName = b.typeName;
    var str = '';
    str += '<div class="card m-border-0 mb-4"><div class="card-body overflow-hidden"><div class="row"><div class="col-3">';
    str += '<img src="'+imageUrl+'" style="width: 100%;" class="rounded"></div><div class="col-9">';
    str += '<h4 class="text-dark mb-3">'+title+'</h4>';
    str += '<p class="card-text font-weight-light mt-2 text-dark">'+content+'</p>';
    str += '<a href="/blog/get/'+blogId+'" class="btn m-btn-css btn-sm float-right">查看详情</a>';
    str += '</div></div></div><div class="card-footer bg-transparent border-light small text-secondary">';
    str += '<span class="mr-4"><i class="fa fa-user mr-1" aria-hidden="true"></i><span>'+author+'</span></span>';
    str += '<span class="mr-4"><i class="fa fa-calendar mr-1" aria-hidden="true"></i><span>'+time+'</span></span>';
    str += '<span class="mr-4"><i class="fa fa-eye mr-1" aria-hidden="true"></i><span>'+readNumber+'</span></span>';
    str += '<span class="mr-4"><i class="fa fa-comments-o mr-1" aria-hidden="true"></i><span>'+linkNumber+'</span></span>';
    str += '<span class="mr-4"><i class="fa fa-eye mr-1" aria-hidden="true"></i><span>'+typeName+'</span></span>';
    str += '<span class="mr-4"><i class="fa fa-eye mr-1" aria-hidden="true"></i><span>'+labelName+'</span></span></div></div>';
    return str;
}

//============================= 分类及标签页面博客列表相关 END ==========================

//日期格式化，将毫秒转为 XXXX-XX-XX 的格式
Date.prototype.Format = function(fmt) {
    var o = {
        "M+" : this.getMonth() + 1, // 月份
        "d+" : this.getDate(), // 日
        "h+" : this.getHours(), // 小时
        "m+" : this.getMinutes(), // 分
        "s+" : this.getSeconds(), // 秒
        "q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
        "S" : this.getMilliseconds()
        // 毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
            .substr(4 - RegExp.$1.length));
    for ( var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
                : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;

};