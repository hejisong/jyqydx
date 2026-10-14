$(document).ready(function() {
  var length = $(".content_1").children(".box").length;//���Ӹ���
  var boxWidth = $(".bigbox").width() / 4;//�Ӵ���ȳ���4����ƶ����
  var virtual = length * boxWidth;	//�л����ٽ��
  var speed = 500;	//�ƶ��ٶȣ��ٶȽ���ҪС�ڼ��ʱ���һ�롣
  var time =4000;	//���ʱ��
  $(".box").width(boxWidth-20);

  var Item = $('#switcher'); //Ҫ�ƶ���Ԫ��
  Item.css({ position: 'relative' }); //����position
  var move = boxWidth + 'px'; //�ƶ��ķ�Χ����һ��box�Ŀ�ȡ�
  var leftCriticalPoint = "-" + virtual + "px"; //��n�����ӣ�����n�����ӵĳ�����Ϊ��Ծ��

  var flag = true;//�������

  scrollContentStructure(length);

  function scrollContentStructure(length) {
    if(length < 4) {
      $('#switcher').width(boxWidth * (length + 4)); //�Ӵ���� ��״��l+4������6-l������l=3����״��7.����3
      if(length != 0) {
        var content_1 = $(".content_1").html();
        for(var i = 0; i < 6 - length; i++) {
          $(".content_1").append(content_1); //����6�����ӣ�����
        }
      }
    } else {
      $('#switcher').width(virtual * 2);
      $(".content_2").html($(".content_1").html()); //����
    }
  }

  if(length != 0) {
    var callback = setInterval(moving, time);
  }

  function moving() {
    flag = false;
    if(Item[0].style.left == leftCriticalPoint) {
      Item[0].style.left = "0px";
    }
    Item.animate({ left: '-=' + move }, speed, function() {
      if(Item[0].style.left == leftCriticalPoint) {
        Item[0].style.left = "0px";
      }
    });
    flag = true;
  }

  $("li").click(function() {
    //��ǰ���ڶ���״̬���ɵ��״̬�ж�
    //��־λ��ֹ�¼�ջ���ۣ�
    if(!Item.is(":animated") && flag) {
      var left = Item[0].style.left;
      clearInterval(callback);

      if($(this).index() == 1) {
        if(left == leftCriticalPoint) {
          Item[0].style.left = "0px";
        }
        Item.animate({ left: '-=' + move }, speed, function() {
          if(Item[0].style.left == leftCriticalPoint) {
            Item[0].style.left = "0px";
          }
          callback = setInterval(moving, time);
        });
        // console.log("��");
      } else if($(this).index() == 0) {
        if(isNaN(parseInt(left)) || left == "0px") {
          Item[0].style.left = leftCriticalPoint;
        }
        Item.animate({ left: '+=' + move }, speed, function() {
          if(Item[0].style.left == "0px") {
            Item[0].style.left = leftCriticalPoint;
          }
          callback = setInterval(moving, time);
        });
        // console.log("��");
      }
    }
  });

})
