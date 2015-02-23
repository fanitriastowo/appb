<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
#social:hover {
	-webkit-transform: scale(1.1);
	-moz-transform: scale(1.1);
	-o-transform: scale(1.1);
}

#social {
	-webkit-transform: scale(0.8);
	/* Browser Variations: */
	-moz-transform: scale(0.8);
	-o-transform: scale(0.8);
	-webkit-transition-duration: 0.5s;
	-moz-transition-duration: 0.5s;
	-o-transition-duration: 0.5s;
}

.social-fb:hover {
	color: #3B5998;
}

.social-tw:hover {
	color: #4099FF;
}

.social-sm:hover {
	color: #d34836;
}

.social-em:hover {
	color: #f39c12;
}
</style>
<div class="panel panel-default">
	<div class="panel-footer">
		<div class="text-center center-block">
			<a href="#"><i id="social" class="fa fa-facebook-square fa-2x social-fb" title="Facebook"></i></a> 
			<a href="#"><i id="social" class="fa fa-twitter-square fa-2x social-tw" title="Twitter"></i></a> 
			<a href="http://www.sman-patikraja.sch.id"><i id="social" class="fa fa-sitemap fa-2x social-sm" title="Website"></i></a> 
			<a href="mailto:sman_raja_banyumas@yahoo.com"><i id="social" class="fa fa-envelope-square fa-2x social-em" title="Email"></i></a> 
			<br /> 
			&copy; SMA Negeri Patikraja - Jl. Adipura No. 3 Patikraja Banyumas Telp. 0281-6844576
		</div>
	</div>
</div>