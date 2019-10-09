package com.example.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    /** ユーザーIDとパスワードを取得するSQL */
    private static final String USER_SQL = "SELECT"
            + " user_id,"
            + " password,"
            + " true"
            + " FROM"
            + " m_user"
            + " WHERE"
            + " user_id=?";

    /** ユーザーのロールを取得するSQL */
    private static final String ROLE_SQL = "SELECT"
            + " user_id,"
            + " role"
            + " FROM"
            + " m_user"
            + " WHERE"
            + " user_id=?";

    /**
     * パスワード暗号化
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * セキュリティ設定(除外)
     */
    @Override
    public void configure(WebSecurity web) throws Exception {

        // 静的リソースに対してのセキュリティを非適用にする
        web.ignoring().antMatchers("/webjars/**", "/css/**");
    }

    /**
     * アクセス設定
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //================
        // 直リンクアクセス設定
        //================
        http.authorizeRequests()

                // webjarsへアクセス許可
                .antMatchers("/webjars/**").permitAll()
                // cssへのアクセス許可
                .antMatchers("/css/**").permitAll()
                // ログインページへのアクセス許可
                .antMatchers("/login").permitAll()
                // ユーザー登録画面へのアクセス許可
                .antMatchers("/signup").permitAll()
                // RESTサービスはアクセス許可
                .antMatchers("/rest/**").permitAll()

                // アドミン画面はadmin権限があるユーザーのみアクセスできる
                .antMatchers("/admin").hasAuthority("ROLE_ADMIN")

                // 上記以外のページは直リンクでのアクセス禁止
                .anyRequest().authenticated();

        //================
        // 認証が必要なログインの設定
        //================
        http.formLogin()

                // ログイン処理を行うパス
                .loginProcessingUrl("/login")
                // ログインページの設定
                .loginPage("/login")
                // ログイン失敗時の遷移先
                .failureUrl("/login")

                // ログインページのユーザーID
                .usernameParameter("userId")
                // パスワード
                .passwordParameter("password")
                // ログイン成功時の遷移先
                .defaultSuccessUrl("/home", true);

        //================
        //ログアウト設定
        //================
        http.logout()
                // ログアウト処理でGETメソッドを使う場合は、logoutRequestMatcherを使う
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                // POSTメソッドでログアウトする場合の設定
                .logoutUrl("/logout")
                // ログアウト成功時の遷移先
                .logoutSuccessUrl("/login");

        //================
        // CSRF対策設定
        //================

        // CSRF対策を無効にするURLを設定
        RequestMatcher csrfMatcher = new RestMatcher("/rest/**");

        // RESTのみCSRF対策を無効に設定
        http.csrf().requireCsrfProtectionMatcher(csrfMatcher);

        //TODO CSRF対策を設定(コメントアウト)
        //http.csrf().disable();
    }

    /**
     * ログイン時の認証設定
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // ユーザーデータの取得
        auth.jdbcAuthentication()
                .dataSource(dataSource)

                // ログイン処理時のユーザー情報をDBから取得する
                .usersByUsernameQuery(USER_SQL)
                .authoritiesByUsernameQuery(ROLE_SQL);
    }

}
