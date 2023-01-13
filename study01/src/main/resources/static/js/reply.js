const replyService = (function(){
	function insert(reply,callback){
		console.log("인서트");
		$.ajax({
			type:"POST",
			url:"/reply/regist",
			data:JSON.stringify(reply),
			contentType:"application/json; charset=utf-8",
			success:function(result,status,xhr){
				if(callback){
					callback(result);
				}
			},
			error:function(){
				
			}
		})
	}
	function selectAll(data,callback){
		let boardnum = data.boardnum;
		let pagenum = data.pagenum;
		
		//ajax
		//$.getJSON(요청URI,성공시 호출할 함수).fail(실패시 호출할 함수)
		$.getJSON(
			"/reply/pages/"+boardnum+"/"+pagenum+".json",
			//위의 uri의 JSON을 정상적으로 읽어왔다면 아래에 있는 함수를 호출해준다.
			//그 때 매개변수 data에는 읽어온 json 내용이 담기게 된다.
			function(data){
				//data : {replyCnt:댓글개수, list:[ReplyDTO, ...]}
				if(callback){
					callback(data.replyCnt, data.list);
				}
			}
		).fail(function(){})
	}
	// /reply/123
	function drop(replynum,callback,error){
		$.ajax({
			type:"DELETE",
			url:"/reply/"+replynum,
			success:function(result, status, xhr){
				if(callback){
					callback(result);
				}
			},
			error:function(xhr, status, err){
				if(error){
					error(err);
				}
			}
		})
	}
	// /reply/123
	function update(reply,callback){
		$.ajax({
			type:"PUT",
			url:"/reply/"+reply.replynum,
			data:JSON.stringify(reply),
			contentType:"application/json; charset=utf-8",
			success:function(result){
				if(callback){
					callback(result);
				}
			},
			error:function(err){
				alert("에러발생 : "+err);
			}
		})
	}
	
	
	function fmtTime(reply){
		let regdate = reply.regdate;
		let updatedate = reply.updatedate
		const now = new Date();
		let check = regdate == updatedate;
		
		const dateObj = new Date(check?regdate:updatedate);
		//date객체.getTime() : 시간 정보를 밀리초 단위로 추출
		let gap = now.getTime() - dateObj.getTime();
		
		let str = "";
		if(gap < 1000*60*60*24){
			let hh = dateObj.getHours();
			let mi = dateObj.getMinutes();
			let ss = dateObj.getSeconds();
			
			str = (hh>9?'':'0')+hh+":"+(mi>9?'':'0')+mi+":"+(ss>9?'':'0')+ss;
		}
		else{
			let yy = dateObj.getFullYear();
			let mm = dateObj.getMonth()+1;
			let dd = dateObj.getDate();
			
			str = yy+"/"+(mm>9?'':'0')+mm+"/"+(dd>9?'':'0')+dd;			
		}
		
		return (check?'':'(수정됨) ')+str;
	}
	
	
	return {add:insert, getList:selectAll, remove:drop, modify:update, displayTime:fmtTime}
})()








