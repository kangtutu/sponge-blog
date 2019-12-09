//========================博客列表分页相关部分 START===================================
//将博客列表数据抽取成方法直接进行传参调用即可
function blogLit(){
    var title = '新数据SpringBoot自动装配原理';
    var content = '博客文章简介博客文章简介博客文章简介博客文章简介博客文章简介博客文章简介博客文章简介博客文章简介博客文章简介博客文章简介博客文章简介博客文章简介';
    var imgUrl = '../image/lb-2.jpeg';
    var str = '';
    str += '<div class="card mb-3 m-card-box-shadow"><div class="card-body overflow-hidden"><div class="row"><div class="col-2">';
    str += '<img src="'+imgUrl+'" style="width: 100%;" class="rounded"></div><div class="col-10"><h5 class="text-dark">'+title+'</h5>';
    str += '<p class="card-text small mt-2 text-dark">'+content+'</p><a href="#" class="btn btn-primary btn-sm float-right">查看更多</a></div></div></div>';
    str += '<div class="card-footer bg-transparent border-light small text-secondary">';
    str += '<span class="mr-4"><i class="fa fa-user mr-1" aria-hidden="true"></i>kangtutu</span>';
    str += '<span class="mr-4"><i class="fa fa-calendar mr-1" aria-hidden="true"></i>2019-11-12 12:36</span>';
    str += '<span class="mr-4"><i class="fa fa-eye mr-1" aria-hidden="true"></i>18</span>';
    str += '<span><i class="fa fa-comments-o mr-1" aria-hidden="true"></i>10</span></div></div>';
    $("#blogList").append(str);
}

//点击下一页清空之前数据并填充新数据
function clickNextPage(nextPage){
    var num = nextPage+1;//获取下一页数据
    var blogLimitPage = $("#blogLimitPage");
    blogLimitPage.html("");
    blogLimitPage.append('<div id="blogList">');
    for(i=0;i<10;i++){
        blogLit();
    }
    blogLimitPage.append('</div>');
    //最后添加分页按钮部分
    var limitPage = '';
    limitPage += '<div class="overflow-hidden mt-4">';
    limitPage += '<a href="#" class="btn btn-sm btn-primary" id="topPage">上一页</a>';
    limitPage += '<a href="#" class="btn btn-sm btn-primary float-right" onclick="clickNextPage('+num+')" id="nextPage">下一页</a>';
    limitPage += '</div>';
    $("#blogLimitPage").append(limitPage);
}
//========================留言/评论相关部分 START===================================

function commentList(){
    var str = '<div class="card mb-3" style="border: transparent;box-shadow: 0 0 8px rgba(0,0,0,0.12);"><div class="card-body">';
    str += commentStr('',1);
    if(true){
        str += commentStr('offset-1',2);
    }
    str += '</div>';
    $('#commentLimitList').append(str);
}
//抽取的评论数据页面样式代码，此处需要注意code==1,class样式传col-11；code==2,class样式传col-10
function commentStr(className,code){
    var content = '测试评论回复信息测试评论回复信息测试评论回复信息';
    var col = '';
    if(code == 1){
        col = 'col-11';
    }
    if(code == 2){
        col = 'col-10';
    }
    var str = '';
    str += '<div class="row"><div class="'+className+' col-1 pt-2 pb-2">';
    str += '<p class="font-weight-bold m-auto" style="width: 40px;height: 40px;border-radius: 50%;line-height: 40px;text-align: center;border:1px blue solid;color: blue">S</p>';
    str += '</div><div class="'+col+'"><p class="">'+content+'</p><p class="small border-top pt-1">';
    str += '<span>Som</span>';
    str += '<span>2019-11-12 12:23:22</span></p></div></div>';
    return str;
}










