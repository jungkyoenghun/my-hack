
$(".answer-write input[type=submit]").click(addAnswer);

function addAnswer(e) {
// 서버로 보내는 기본 동작을 막는다.
	e.preventDefault();
	console.log("click !!!!");
	
	var queryString = $(".answer-write").serialize();
	console.log("query : " + queryString);
	
	var url = $(".answer-write").attr("action");
	console.log("url : " + url);
	
	
	$.ajax({
	  type : 'post' ,
	  url : url,
	  data : queryString,
	  dataType : 'json',
	  error : onError,
	  success : onSuccess });
	
}

function onError(){
	
}

function onSuccess(data, status){
	console.log(data);
	var answerTemplate = $("#answerTemplate").html();
	var template = answerTemplate.format(data.writer.userId, data.formattedCreateDate, data.contents, data.question.id, data.id)
    $(".qna-comment-slipp-articles").prepend(template);
	
	$("textarea[name=contents]").val("");
}

$(".link-delete-article").click(deleteAnswer);

function deleteAnswer(e) {
	e.preventDefault();
	//console.log("delete");
	
	//this가 어디에서 호출이 되느냐에 따라 달라지기 때문에, 
	// 변수로 담아 놓고 사용 한다.
	var deleteBtn = $(this);
	
	
	//현재 이벤트가 발생한 부분의 작자신이 this에 들어가게
	var url = deleteBtn.attr("href");
	//console.log(url);
	
	$.ajax({
	
		type : 'delete',
		url : url ,
		dataType : 'json',
		error : function (xhr, status) {
			console.log("error");
			
		},
		success : function (data, status){
			console.log(data);
			//가장 가까이에 있는 aticle을 찾아서 삭제
			if(data.valid) {
				deleteBtn.closest("article").remove();
			}
			else{
				alert(data.errorMessage);
			}
			
		}
		
	})
	
}
	


String.prototype.format = function() {
	  var args = arguments;
	  return this.replace(/{(\d+)}/g, function(match, number) {
	    return typeof args[number] != 'undefined'
	        ? args[number]
	        : match
	        ;
	  });
	};