<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
</section>

<footer>
    <div class="footer_block">
        <div class="socials">
            <p>Follow Yespresso on</p>
            <a href="https://github.com/yeji0401/yespresso-app"><img class="github" src="image/github_white.png" alt="github"></a>
        </div>
        <div class="pagetop">Top of the Page ↑</div>
        <div class="copyright">
            <a href="<%= request.getContextPath() %>"><img class="footer_logo" src="image/logo_white.png" alt="yespresso_LOGO"></a>
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