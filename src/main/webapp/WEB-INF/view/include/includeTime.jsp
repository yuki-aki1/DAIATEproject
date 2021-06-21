<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<p>
	残り時間： <input type="text" id="time" /> 秒
</p>

<script type="text/javascript">
	var counter = 0;
	var timer = null;

	timer = setInterval(countup, 1000);

	// カウンター
	function countup() {
		document.getElementById("time").value = 60 - counter;
		if (counter < 60) {
			counter++;
		}
	}
</script>