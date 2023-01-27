<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
</section>

<footer>
    <div class="footer-block">
        <div class="socials">
            <p>Follow Yespresso on</p>
            <div class="github" onclick="location.href='https://github.com/yeji0401/yespresso-app'"></div>
        </div>
        <div class="pagetop">Top of the Page ↑</div>
        <div class="copyright">
            <div class="footer-logo" onclick="location.href='<%= request.getContextPath() %>'"></div>
            <p>Yespresso 2023</p>
        </div>
    </div>
</footer>
</div>
<script>
const $pagetop = document.querySelector(".pagetop");

$pagetop.onclick = () => {
    window.scrollTo({ top: 0, behavior: "smooth" });  
}
</script>
</body>
</html>