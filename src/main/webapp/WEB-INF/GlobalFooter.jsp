<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <script src="http://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    <script src="http://cdn.bootcss.com/jquery.form/3.50/jquery.form.min.js"></script>
    <script type="text/javascript">
    function hiddenAlert(){
        $("#alert").addClass('animated flipOutX').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function(){
            $(this).removeClass('animated flipOutX');
            $(this).hide();
        });
    }
    function showAlert (){
        $("#alert").show().addClass('animated flipInX').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function(){
            $(this).removeClass('animated flipInX');
        });
    }
    function showSuccessAlert(msg,obj){
        $("#alert").removeClass().addClass("alert alert-success").find("#message").text(msg);
        this.showAlert(obj);
    }
    function showErrorAlert(msg,obj){
        $("#alert").removeClass().addClass("alert alert-error").find("#message").text(msg);
        this.showAlert(obj);
    }
    
    $("form").ajaxForm({
        error:function(data){
            showErrorAlert(data.responseJSON.message);
        },
        success:function(data){
            if(data.redirectUrl){
                	window.location.pathname=data.redirectUrl; 
            }else{
            	showSuccessAlert(data.message);
            }
        }
    });
    $("#alertCloseBtn").click(function(){
    	hiddenAlert();
    });
    </script>
    </body>
</html>
