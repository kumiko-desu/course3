package com.course3.controller;

import com.course3.beans.Article;
import com.course3.beans.Result;
import com.course3.beans.User;
import com.course3.service.ArticleService;
import com.course3.service.UserService;
import com.course3.utils.UserUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
public class UserController {
    @PostMapping ("/register")
    public Result register(@RequestParam("username") String name,
                           @RequestParam String password,
                           boolean isAdmin,
                           @CookieValue(value = "token",required = false) String token){
        User user = UserService.getUserByName(name);
        if (user != null){
            return new Result(false, -1, "user already exist");
        }
        String salt = UserUtils.createSalt();
        String hashPwd = UserUtils.createHashPwd(password, salt);
        if (isAdmin){
            if (token != null && UserService.isAdminByToken(token)) {//&& token is admin
                UserService.addUser(new User(name, salt, hashPwd,true));
                return new Result(true,0,"success to register admin");
            }else{
                return new Result(false,-1,"invalid token to register admin");
            }
        }else{
            UserService.addUser(new User(name, salt, hashPwd,false));
            return new Result(true,0,"success to register normal user");
        }
    }

    @RequestMapping("/login")
    public Result login(HttpServletResponse response,
                        @RequestParam("username") String name,
                        @RequestParam String password){
        User user = UserService.getUserByName(name);
        if (user == null){
            return new Result(false, -1, "user not exist");
        }
        String hashPwd = UserUtils.createHashPwd(password, user.getSalt());
        if (user.getHashPwd().equals(hashPwd)){
            String token = UserService.generateToken(user.getUid());
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(7 * 24 * 60 * 60);
            response.addCookie(cookie);
            return new Result(true,0,"success to login", token);
        } else {
            return new Result(false, -1, "invalid password");
        }
    }

    @PostMapping("/logout")
    public Result logout(HttpServletResponse response,
                         @CookieValue("token") String token){
        if (token.isEmpty()) return new Result(false,-1,"invalid token");
        User user = UserService.getUserByToken(token);
        if (user == null) return new Result(false,-1,"user not exist");
        UserService.generateToken(user.getUid());
        Cookie cookie = new Cookie("token", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return new Result(true,0,"success to logout");
    }

    @PostMapping("/changePassword")
    public Result changePassword(@RequestParam("username") String name,
                                 @RequestParam String password,
                                 @RequestParam String newPassword){
        User user = UserService.getUserByName(name);
        if (user == null){
            return new Result(false, -1, "user not exist");
        }
        String hashPwd = UserUtils.createHashPwd(password, user.getSalt());
        if (user.getHashPwd().equals(hashPwd)){
            if (password.equals(newPassword)){
                return new Result(false,-1,"cannot be the same password");
            }
            UserService.changePassword(user.getUid(), newPassword);
            return new Result(true,0,"success to change password");
        } else {
            return new Result(false, -1, "invalid password");
        }
    }

    @PostMapping("/deleteUser")
    public Result deleteUser(@RequestParam("username") String name,
                             @RequestParam String password){
        User user = UserService.getUserByName(name);
        if (user == null){
            return new Result(false, -1, "user not exist");
        }
        String hashPwd = UserUtils.createHashPwd(password, user.getSalt());
        if (user.getHashPwd().equals(hashPwd)){
            UserService.deleteUserByUid(user.getUid());
            return new Result(true,0,"success to delete");
        } else {
            return new Result(false, -1, "invalid password");
        }
    }

    @RequestMapping("/userList")
    public Result getUserList(@CookieValue("token") String token){
        if (UserService.isAdminByToken(token)){
            List<User> userList = UserService.getUserList();
            return new Result(true,0,"success to get userList", userList);
        }else{
            return new Result(false, -1, "invalid token or no right");
        }
    }

    @RequestMapping("/getName")
    public Result getNameByToken(@CookieValue("token") String token){
        User user = UserService.getUserByToken(token);
        if (user != null){
            return new Result(true,0,"success to get username", user.getName());
        }else{
            return new Result(false, -1, "invalid token");
        }
    }

    @RequestMapping("/setAdmin")
    public Result setAdmin(@RequestParam Integer uid,
                           @CookieValue String token){
        if (UserService.isAdminByToken(token)){
            UserService.setAdminByUid(uid);
            return new Result(true,0,"success to set admin");
        }else{
            return new Result(false, -1,"invalid token or no right to set admin");
        }
    }

    @RequestMapping("/user/delete")
    public Result deleteUser(@RequestParam Integer uid,
                                @CookieValue String token){
        if (UserService.isAdminByToken(token)){
            UserService.deleteUserByUid(uid);
            return new Result(true,0,"success to delete user");
        }else{
            return new Result(false, -1,"invalid token or no right to delete");
        }
    }
}
