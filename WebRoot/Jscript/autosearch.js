jQuery.noConflict();
   jQuery(function() {
		jQuery("#txtSearch").click(function(){
			jQuery.ajax({
		       			url:"autocomplete.do",
						type:"post",
						data:{bookname:jQuery("#txtSearch").val()},
						success:function(returnValue)
						{
						var booksname=eval(returnValue);
						jQuery("#txtSearch").autocomplete(booksname, {
						max:8,
						selectFirst:false,
						multipleSeparator:" "
                			}) 
						}
		
				})
			})
			jQuery("#txtSearch").trigger("focus");   
        })