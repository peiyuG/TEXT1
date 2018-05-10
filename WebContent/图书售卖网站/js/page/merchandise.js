$(function(){
	// 获取所有图书
	$.ajax({
		url: '/Bookshops/book/GetAllBookMsg',
		dataType:'json',
		success(data){
			if(data.code == 1){
				let str = '';
				for(let i=0;i<data.data.length;i++){
					str+=`<div class="col-xs-6 col-md-3" data-bookId="${data.data[i].bookId}">
							<div class="thumbnail">
								<img src="${data.data[i].bookImg}" class="item">
								<div class="caption">
									<h2 class="book-name">${data.data[i].bookName}</h2>
									<p class="book-intro" title="${data.data[i].bookIntroduction}">${data.data[i].bookIntroduction}</p>
									<p class="btn-wrap">
										<button class="btn btn-primary buyBtn">购买</button>
										<button class="btn btn-info viewBtn" data-toggle="modal" data-target="#item-info">查看</button>
									</p>
								</div>
							</div>
						</div>`
				}
				$('.merchandise-list .row').append(str);
			}
		}
	}).then((value)=>{
		$('.viewBtn').click(function(){
			let bookId = $(this).parents('.col-md-3').attr('data-bookId');
			let bookItemInfo;
			for(let i=0;i<value.data.length;i++){
				if(value.data[i].bookId == bookId){
					bookItemInfo = value.data[i];
					break;
				}
			}
			let $boxItem = $('#item-info');
			$boxItem.find('.book-name').html(bookItemInfo.bookName);
			$boxItem.find('.book-item-intro').html(bookItemInfo.bookIntroduction);
			$boxItem.find('#category').html(bookItemInfo.bookClass);
			$boxItem.find('#price').html(bookItemInfo.bookPrice);
			$boxItem.find('#publishHouse').html(bookItemInfo.bookPublishinghouse);
		})
		$('.buyBtn').click(function(){
			let bookId = $(this).parents('.col-md-3').attr('data-bookId')
			let bookItemInfo;
			for(let i=0;i<value.data.length;i++){
				if(value.data[i].bookId == bookId){
					bookItemInfo = value.data[i];
					break;
				}
			}
			$.ajax({
				url:'/Bookshops/order/AddOrder',
				data:{bookId,bookName:bookItemInfo.bookName},
				type:'post',
				success(data){
					if(data.code == 1){
						alert(data.msg+',请在个人中心结算')
					}
				}
			})
		})
	})
})