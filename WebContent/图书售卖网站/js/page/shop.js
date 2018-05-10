$(function() {
	// 获取上架图书
	let $modBook;
	$.ajax({
		url:'/Bookshops/book/GetAllBookMsg',
		dataType:'json',
		success(data){
			if(data.code == 1){
				let str='';
				for(let i=0;i<data.data.length;i++){
					str+=`<tr>
							<td>${data.data[i].bookId}</td>
							<td>
								<span class="bookname">${data.data[i].bookName}</span>
							</td>
							<td>
								<span class="classification">${data.data[i].bookClass}</span>
							</td>
							<td>
								<span class="price">${data.data[i].bookPrice}</span>
							</td>
							<td>
								<p class="intro">${data.data[i].bookIntroduction}</p>
							</td>
							<td>
								<button class="btn btn-primary modefied">修改</button>
								<button class="btn btn-danger deleteBtn">删除</button>
							</td>
						</tr>`
				}
				$('#bookTable tbody').append(str);
			}
		}
	}).then(function(value){
		$('.modefied').click(function() {
			let $ele = $(this).parents('tr');
			$modBook = $ele;
			let book = {
				id: $ele.find('td:first').html(),
				name: $ele.find('.bookname').html(),
				classification: $ele.find('.classification').html(),
				price: $ele.find('.price').html(),
				intro: $ele.find('.intro').html()
			}
			let $box = $('.modefied-box');
			$box.slideDown();
			let $boxItem = $box.find('.form-group');
			let values = Object.values(book);
			for (let i = 0; i < values.length; i++) {
				if (i == 4) {
					$boxItem.eq(i).find('textarea').html(values[i]);
				} else {
					$boxItem.eq(i).find('input').attr('value', values[i]);
				}
			}
			$('.cover-layer').show();
			let wholeHeight = $(document).height();
			$('.cover-layer').css({
				height: wholeHeight
			})
		})
		$('.deleteBtn').click(function(){
			let $ele = $(this).parents('tr');
			let bookId = $(this).parent().siblings(':first').html();
			$.ajax({
				url:'/Bookshops/book/BookDelete',
				data:{bookId},
				type:'post',
				success(data){
					if(data.code == 1){
						alert('删除成功');
						$ele.remove();
					}
				}
			})
		})
	})
	$('#modclose').click(function() {
		$('.modefied-box').slideUp();
		$('.cover-layer').hide();
	})
	// 修改商品信息
	$('#checkBtn').click(function(){
		// let formData = new FormData($('#modefiedForm'));
		// let bookId = $modBook.find('td:eq(0)').html();
		// console.log(bookId)
		// formData.append("bookId",bookId);
		$('#modefiedForm').on('submit',function(e){
			e.preventDefault();
			$(this).ajaxSubmit({
				url:'/Bookshops/book/BookUpdate',
				type:'post',
				success(data){
					if(data.code == 1){
						alert('修改成功成功')
						$('#modclose').trigger('click')
					}
				}
			})
		})
		// $.ajax({
		// 	url:'http://rap.taobao.org/mockjsdata/30567/book/UpdateBookServlet',
		// 	type:'post',
		// 	data:formData,
		// 	processData:false,
		// 	contentType:false,
		// 	cache:false,
		// 	success(data){
		// 		// console.log(data)
		// 		if(data.code == 1){
		// 			alert(data.msg)
		// 			$('#modclose').trigger('click');
		// 		}
		// 	}
		// }).then(function(){
		// 	// window.location.reload()
		// })
		// return false;  
	})
	// 表单验证 添加商品
	$('#addForm').validate();
	$('#addBtn').click(function(){
		// console.log($('#addForm').valid())
		if($('#addForm').valid()){
			$('#addForm').on('submit',function(e){
				e.preventDefault();
				$(this).ajaxSubmit({
					url:'/Bookshops/book/BookAdd',
					type:'post',
					success(data){
						console.log(data)
						if(data.code == 1){
							alert('添加成功')
							$('#closeBtn').trigger('click')
						}
					}
				})
			})
			// let formData = new FormData($('#addForm'));
			// $.ajax({
			// 	url:'http://rap.taobao.org/mockjsdata/30567/book/AddBookServlet',
			// 	type:'post',
			// 	data:formData,
			// 	processData:false,
			// 	contentType:false,
			// 	cache:false,
			// 	success(data){
			// 		// console.log(data)
			// 		if(data.code == 1){
			// 			alert(data.msg)
			// 			$('#closeBtn').trigger('click')
			// 		}
			// 	},
			// 	error(){
			// 		console.log('请求失败')
			// 	}
			// }).then(function(){
			// 	// window.location.reload()
			// })
		}
	})
})