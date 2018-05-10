$(function(){
	$.ajaxSetup({
		dataType:'json',
		error(){
			console.log('请求失败')
		}
	})
	// 获取用户信息
	$.ajax({
		url:'/Bookshops/user/UserMsg',
		success(data){
			if(data.code == 1){
				$('.info:eq(0)').html(data.data[0].userName)
				$('.info:eq(1)').html(data.data[0].password)
			}
		}
	})
	// 获取用户订单
	$.ajax({
		url:'/Bookshops/order/getOrder',
		success(data){
			if(data.code == 1){
				let str = ``;
				for(let i=0;i<data.data.length;i++){
					if(data.data[i].orderStatus == 0){
						str+=`<tr>
								<td>${data.data[i].orderId}</td>
								<td>${data.data[i].bookName}</td>
								<td><button class="btn btn-warning payBtn">待支付</button></td>
							</tr>`
					}else if(data.data[i].orderStatus == 1){
						str+=`<tr>
								<td>${data.data[i].orderId}</td>
								<td>${data.data[i].bookName}</td>
								<td>已支付</td>
							</tr>`
					}
				}
				$('#order').append(str);
			}else{
				$('#order').append(`<tr>
										<td><p class="text-center">您还没有任何订单快去看看吧:)</p></td>
									</tr>`)
			}
		}
	}).then(function(){
		$('.payBtn').click(function(){
			let $btn = $(this)
			let orderId = $(this).parent().siblings(':first').html();
			$.ajax({
				url:'/Bookshops/order/PayForOrder',
				type:'post',
				data:{orderId},
				success(data){
					if(data.code == 1){
						alert(data.msg)
						$btn.replaceWith('已支付')
					}
				}
			})
		})
	})
	// 修改信息
	$('#modefied').click(function(){
		let $item = $('.user-box');
		let userName = $item.find('span:eq(0)').html(),
			password = $item.find('span:eq(0)').html();
		$item.find('span:eq(0)').replaceWith(`<input type="text" value="${userName}" class="form-control info">`)
		$item.find('span:eq(0)').replaceWith(`<input type="password" value="${password}" class="form-control info">`)
		$('#submitInfo').removeAttr("disabled");
	})
	$('#submitInfo').click(function(){
		let userName = $('.info').eq(0).val(),
			password = $('.info').eq(1).val();
		// 修改信息
		$.ajax({
			url:'/Bookshops/user/UserUpData',
			data:{userName,password},
			type:'post',
			success(data){
				if(data.code == 1){
					alert(data.msg)
					$('.user-box').find('input:eq(0)').replaceWith(`<span class="info">${userName}</span>`)
					$('.user-box').find('input:eq(0)').replaceWith(`<span class="info">${password}</span>`)
					$('#submitInfo').attr('disabled','disabled');
				}
			}
		})
	})
})