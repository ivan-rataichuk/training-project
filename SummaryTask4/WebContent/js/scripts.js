$(document).ready(function(){
	
	$(".close").click(function(){
		$("#mes").hide();
	});
	
	$(".submit").click(function(){
		this.form.submit()
	});
	
	$('.sub-select').change(function() {
    resetOptions();
	    $(".sub-select").each(function(){
	        var selectedVal = $(this).val();
	        var attrID = $(this).prop("id");
	        $(".sub-select").each(function(){
	            if($(this).prop("id") != attrID){
	                if(selectedVal != ""){
	                    $(this).children("option[value="+selectedVal+"]").hide();
	                }
	            }
	        });
	    });
	});
});

function resetOptions(){
    $(".sub-select").each(function(){
        $(this).children("option").show();
    });
}

