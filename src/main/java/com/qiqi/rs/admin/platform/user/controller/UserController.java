package com.qiqi.rs.admin.platform.user.controller;

import com.github.pagehelper.Page;
import com.qiqi.core.byenum.SortOrderEnum;
import com.qiqi.core.model.Result;
import com.qiqi.core.utils.PageCondition;
import com.qiqi.rs.admin.core.annotation.Permission;
import com.qiqi.rs.admin.core.constants.IConstants;
import com.qiqi.rs.admin.core.model.PermissionEnum;
import com.qiqi.rs.admin.core.runtime.threadlocal.UserContextThreadLocalHolder;
import com.qiqi.rs.admin.platform.user.model.CachedUser;
import com.qiqi.rs.admin.platform.user.model.RoleEnum;
import com.qiqi.rs.admin.platform.user.model.UserInfo;
import com.qiqi.rs.admin.platform.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统用户
 */
@RestController
@RequestMapping("/platform/users")
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    @Permission(PermissionEnum.EDIT)
    @RequestMapping(method = RequestMethod.POST)
    public Result<Integer> createUser(@RequestBody UserInfo userInfo) {
        return new Result<Integer>(this.userInfoService.createUser(userInfo));
    }

    @Permission(PermissionEnum.EDIT)
    @RequestMapping(method = RequestMethod.PATCH)
    public Result<Integer> updateUser(@RequestBody UserInfo userInfo) {
        return new Result<Integer>(this.userInfoService.updateUser(userInfo));
    }

    @Permission(PermissionEnum.EDIT)
    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public Result<Integer> deleteUser(@PathVariable(value = "userId", required = true) String userId) {
        return new Result<Integer>(this.userInfoService.deleteUser(userId));
    }

    /**
     * 生成新编号
     */
    @RequestMapping(value = "/gencode", method = RequestMethod.GET)
    public Result<String> gencode() {
        return new Result<String>(this.userInfoService.queryNextCode());
    }

    @Permission(permission = PermissionEnum.SEARCH)
    @RequestMapping(method = RequestMethod.GET)
    public Result<List<UserInfo>> queryUsers(UserInfo userInfo,
                                             @RequestParam(defaultValue = "1") int pageIndex,
                                             @RequestParam(defaultValue = "10") int pageSize,
                                             @RequestParam(defaultValue = "createdTime") String sortField,
                                             @RequestParam(defaultValue = "asc") String sortType) {
        PageCondition pageCondition = new PageCondition(pageIndex, pageSize, true, sortField, SortOrderEnum.valueFrom(sortType));
        List<UserInfo> list = this.userInfoService.queryUsers(userInfo, pageCondition);
        Page page = (Page) list;
        return new Result<>(page.getTotal(), list);
    }

    @RequestMapping(value = "/basic", method = RequestMethod.GET)
    public Result<List<UserInfo>> queryUserBasicInfo(UserInfo userInfo,
                                                     @RequestParam(defaultValue = "1") int pageIndex,
                                                     @RequestParam(defaultValue = "1000") int pageSize,
                                                     @RequestParam(defaultValue = "createdTime") String sortField,
                                                     @RequestParam(defaultValue = "asc") String sortType) {
        PageCondition pageCondition = new PageCondition(pageIndex, pageSize, false, sortField, SortOrderEnum.valueFrom(sortType));
        List<UserInfo> userInfos = this.userInfoService.queryUsersBasicInfo(userInfo, pageCondition);
        Page page = (Page) userInfos;
        return new Result<>(page.getTotal(), userInfos);
    }

    @RequestMapping(value = "current", method = RequestMethod.GET)
    public Result<UserInfo> queryCurrentUser() {
        CachedUser cachedUser = UserContextThreadLocalHolder.get();
        if (cachedUser == null) {
            return new Result<>("0", "");
        }

        return new Result<>(this.userInfoService.queryUserById(cachedUser.getUserId()));
    }


    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public Result<UserInfo> queryUserInfoById(@PathVariable(name = "userId") String userId) {
        return new Result<>(this.userInfoService.queryUserById(userId));
    }

    /**
     * 获取推荐人列表
     *
     * @return
     */
    @RequestMapping(value = "/referrers", method = RequestMethod.GET)
    public Result<List<UserInfo>> querySaleUsers() {
        List<UserInfo> userInfos = this.userInfoService.queryUsersBasicInfo(new UserInfo() {{
            setRole(RoleEnum.SALE.code());
        }}, new PageCondition(1, 1000, false, IConstants.QUERY_SORT_CREATED_TIME, SortOrderEnum.ASC));
        if (userInfos != null && !userInfos.isEmpty()) {
            for (UserInfo userInfo : userInfos) {
                userInfo.setDelFlag(null);
            }
        }
        return new Result<>(userInfos);
    }
}
