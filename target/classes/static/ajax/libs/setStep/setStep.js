/**
 * Created by changwang.song on 2017/12/26.
 */
function extend(obj1,obj2){
    for(var attr in obj2){
        obj1[attr] =  obj2[attr];
    }
}
function SetStep(arg){
    this.body=document.body;
    this.opt = {
        btnText:[',填写发起',',填写募捐',',填写进行',',填写结束',',填写庆祝'],
        show:false,
        content:'.stepCont',
        pageCont:'.pageCont',
        fontWidth:100,
        imgWidth:20,
        stepContainerMar:50,
        nextBtn:'.nextBtn',
        prevBtn:'.prevBtn',
        steps:['发起','募捐','进行','结束','庆祝'],
        //pageClass:'',//分页的类或则id
        stepCounts:5,//总共的步骤数
        curStep:1,//当前显示第几页
        animating:false,
        showBtn:true,//是否生成上一步下一步操作按钮
        clickAble:true,//是否可以通过点击进度条的节点操作进度
        onLoad: function(){},
        onChangeBefore:function(){
            return true;
        },
        onChangeAfter:function () {
            return true;
        }
    }
    this.init(arg)
}
//初始化 生成页面的进度条和按钮
SetStep.prototype.init=function(arg){
    var _that=this;
    extend(this.opt,arg);
    this.opt.stepCounts=this.opt.steps.length;
    this.content=$(this.opt.content);
    this.pageCont=this.content.find(this.opt.pageCont)
    var w_con=$(this.content).width();
    var w_li=(w_con-this.opt.stepContainerMar*2)/this.opt.stepCounts/2;
    var stepContainer=this.content.find('.ystep-container');
    this.stepContainer=stepContainer;
    var stepsHtml=$("<ul class='ystep-container-steps'></ul>");
    var stepDisc = "<li class='ystep-step ystep-step-undone'></li>";
    var stepP=$("<div class='ystep-progress'>"+
                "<p class='ystep-progress-bar'><span class='ystep-progress-highlight' style='width:0%'></span></p>"+
            "</div>");
    var stepButtonHtml =$( "<div class='step-button'><button type='button' class='btn btn-default prevBtn' id='prevBtn' class='prevBtn'>上一步</button>"+
                        "<button type='button' class='btn btn-default nextBtn' id='nextBtn' class='nextBtn'>下一步</button></div>");
    stepP.css('width',w_li*2*(this.opt.stepCounts-1));
    stepP.find('.ystep-progress-bar').css('width',w_li*2*(this.opt.stepCounts-1))

    for(var i=0;i<this.opt.stepCounts;i++){
        if(i==0){
            var _s=$(stepDisc).html(this.opt.steps[i]).addClass('')
        }else{
            var _s=$(stepDisc).html(this.opt.steps[i])
        }
        stepsHtml.append(_s);
    }
    stepsHtml.find('li').css('width',this.opt.fontWidth+'px').css('marginRight',w_li*2-this.opt.fontWidth)
    stepContainer.append(stepsHtml).append(stepP);

    stepContainer.css('left',(w_con-stepP.width()-this.opt.imgWidth-10)/2)

    this.content.css('overflow','hidden')
    this.setProgress(this.stepContainer,this.opt.curStep,this.opt.stepCounts)
    //判断参数 是否显示按钮 并绑定点击事件
    if(this.opt.showBtn) {
        //this.content.append(stepButtonHtml)
    }
    this.prevBtn=this.content.find(this.opt.prevBtn)
    this.nextBtn=this.content.find(this.opt.nextBtn)
    //绑定事件
    this.prevBtn.on('click',function (){
        _that.goToPrev(_that,true)
    })
    this.nextBtn.on('click',function (){
        _that.goToNext(_that,true)
    })

    //判断时候可点击进度条 并绑定点击事件
    if(this.opt.clickAble){
        stepsHtml.find('li').on('click',function(){
            var thisStep = $(this).index();
            var bool = _that.opt.onChangeBefore(thisStep);
            if(!bool){
                return false;
            }
            _that.opt.curStep = thisStep+1;
            _that.setProgress(_that.stepContainer,_that.opt.curStep,_that.opt.stepCounts);
    })
    }
     $(window).resize(function(){
        var w_con=$(_that.content).width();
         var w_li=(w_con-_that.opt.stepContainerMar*2)/_that.opt.stepCounts/2;
        stepP.css('width',w_li*2*(_that.opt.stepCounts-1));
        stepP.find('.ystep-progress-bar').css('width',w_li*2*(_that.opt.stepCounts-1))
        stepsHtml.find('li').css('width',_that.opt.fontWidth+'px').css('marginRight',w_li*2-_that.opt.fontWidth)
        stepContainer.css('left',(w_con-stepP.width()-_that.opt.imgWidth-10-_that.opt.stepContainerMar*2)/2)
     })
}
//上一步
SetStep.prototype.goToPrev = function(_that,active){

    if(active==true){
        //点击之前事件
        var thisStep = _that.opt.curStep;
        var bool = _that.opt.onChangeBefore(thisStep,-1);
        if(!bool){
            return false;
        }
    }

    if($(_that).attr('hidden')||_that.opt.animating){
        return false;
    }else{
        _that.opt.animating=true;
        _that.opt.curStep--;
        _that.setProgress(_that.stepContainer,_that.opt.curStep,_that.opt.stepCounts,active,-1)
    }
}
//下一步
SetStep.prototype.goToNext = function(_that,active){
    if(active==true){
        //点击之前事件
        var thisStep = _that.opt.curStep;
        var bool = _that.opt.onChangeBefore(thisStep,1);
        if(!bool){
            return false;
        }
    }
    if($(_that).attr('hidden')||_that.opt.animating){
        return false;
    }else{
        _that.opt.animating=true;
        _that.opt.curStep++;
        _that.setProgress(_that.stepContainer,_that.opt.curStep,_that.opt.stepCounts,active,1)
    }
}

//设置进度条
SetStep.prototype.setProgress=function(n,curIndex,stepsLen,active,flag){
        var _that=this;
        //获取当前容器下所有的步骤
        var $steps = $(n).find("li");
        var $progress =$(n).find(".ystep-progress-highlight");
        //判断当前步骤是否在范围内
        if(1<=curIndex && curIndex<=$steps.length){
          //更新进度
          var scale = "%";
          scale = Math.round((curIndex-1)*100/($steps.length-1))+scale;
          $progress.animate({
            width: scale
          },{
            speed: 1000,
            done: function() {
              //移动节点
              $steps.each(function(j,m){
                var _$m = $(m);
                var _j = j+1;
                if(_j < curIndex){
                  _$m.attr("class","ystep-step-done");
                }else if(_j === curIndex){
                  _$m.attr("class","ystep-step-active");
                }else if(_j > curIndex){
                  _$m.attr("class","ystep-step-undone");
                }
              })
              if(_that.opt.showBtn){
                  if(curIndex==1){
                      _that.prevBtn.addClass('hidden')
                      _that.nextBtn.removeClass('hidden')
                      _that.nextBtn.find("span").text(_that.opt.btnText[curIndex]);
                  }else if(curIndex==stepsLen){
                      _that.prevBtn.removeClass('hidden')
                      _that.nextBtn.addClass('hidden','true')
                      _that.prevBtn.find("span").text(_that.opt.btnText[curIndex-2]);
                  }else if(1<curIndex<stepsLen){
                      _that.prevBtn.removeClass('hidden')
                      _that.nextBtn.removeClass('hidden')
                      _that.prevBtn.find("span").text(_that.opt.btnText[curIndex-2]);
                      _that.nextBtn.find("span").text(_that.opt.btnText[curIndex]);
                  }
              }
               _that.checkPage(_that.pageCont,_that.opt.curStep,_that.opt.stepCounts)
               _that.opt.animating=false;
              if(active==true){
                  //之后事件
                  _that.opt.onChangeAfter(_that.opt.curStep,flag);
              }
            }
          });
        }else{
            return false;
        }
}
//改变 分页显示
SetStep.prototype.checkPage=function(pageCont,curStep,steps){
    var _that=this;
    for(var i = 1; i <= steps; i++){
        if(i === curStep){
          pageCont.find('#page'+i).css("display","block");
        }else{
          pageCont.find('#page'+i).css("display","none");
        }
    }
}