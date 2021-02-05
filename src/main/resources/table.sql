/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50562
 Source Host           : localhost:3306
 Source Schema         : zhmoban

 Target Server Type    : MySQL
 Target Server Version : 50562
 File Encoding         : 65001

 Date: 03/09/2020 11:33:36
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `config_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '参数配置表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
INSERT INTO `sys_config` VALUES (2, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '初始化密码 123456');
INSERT INTO `sys_config` VALUES (3, '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '深色主题theme-dark，浅色主题theme-light');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint(20) DEFAULT 0 COMMENT '父部门id',
  `ancestors` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '部门名称',
  `order_num` int(4) DEFAULT 0 COMMENT '显示顺序',
  `leader` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 120 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (100, 0, '0', '全部', 0, '管理员', '', '', '0', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2020-09-03 10:12:32');
INSERT INTO `sys_dept` VALUES (118, 100, '0,100', '测试12312312', 1, '测试001123123', '19771909291', '123123123@qq.com', '0', '2', '', '2020-09-03 10:11:34', '', '2020-09-03 10:12:32');
INSERT INTO `sys_dept` VALUES (119, 100, '0,100', '测试', 1, '测试001', '18778909291', NULL, '0', '0', '', '2020-09-03 10:13:34', '', NULL);

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `dict_code` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(4) DEFAULT 0 COMMENT '字典排序',
  `dict_label` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 154 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典数据表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '性别男');
INSERT INTO `sys_dict_data` VALUES (2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '性别女');
INSERT INTO `sys_dict_data` VALUES (3, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '性别未知');
INSERT INTO `sys_dict_data` VALUES (4, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '显示菜单');
INSERT INTO `sys_dict_data` VALUES (5, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '隐藏菜单');
INSERT INTO `sys_dict_data` VALUES (6, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES (7, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '停用状态');
INSERT INTO `sys_dict_data` VALUES (8, 1, '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES (9, 2, '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '停用状态');
INSERT INTO `sys_dict_data` VALUES (10, 1, '默认', 'DEFAULT', 'sys_job_group', '', '', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '默认分组');
INSERT INTO `sys_dict_data` VALUES (11, 2, '系统', 'SYSTEM', 'sys_job_group', '', '', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统分组');
INSERT INTO `sys_dict_data` VALUES (12, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统默认是');
INSERT INTO `sys_dict_data` VALUES (13, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统默认否');
INSERT INTO `sys_dict_data` VALUES (14, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知');
INSERT INTO `sys_dict_data` VALUES (15, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '公告');
INSERT INTO `sys_dict_data` VALUES (16, 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES (17, 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '关闭状态');
INSERT INTO `sys_dict_data` VALUES (18, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '新增操作');
INSERT INTO `sys_dict_data` VALUES (19, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '修改操作');
INSERT INTO `sys_dict_data` VALUES (20, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '删除操作');
INSERT INTO `sys_dict_data` VALUES (21, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '授权操作');
INSERT INTO `sys_dict_data` VALUES (22, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '导出操作');
INSERT INTO `sys_dict_data` VALUES (23, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '导入操作');
INSERT INTO `sys_dict_data` VALUES (24, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '强退操作');
INSERT INTO `sys_dict_data` VALUES (25, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '生成操作');
INSERT INTO `sys_dict_data` VALUES (26, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '清空操作');
INSERT INTO `sys_dict_data` VALUES (27, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES (28, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '停用状态');

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '字典类型',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`) USING BTREE,
  UNIQUE INDEX `dict_type`(`dict_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典类型表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1, '用户性别', 'sys_user_sex', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '用户性别列表');
INSERT INTO `sys_dict_type` VALUES (2, '菜单状态', 'sys_show_hide', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '菜单状态列表');
INSERT INTO `sys_dict_type` VALUES (3, '系统开关', 'sys_normal_disable', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统开关列表');
INSERT INTO `sys_dict_type` VALUES (4, '任务状态', 'sys_job_status', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '任务状态列表');
INSERT INTO `sys_dict_type` VALUES (5, '任务分组', 'sys_job_group', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '任务分组列表');
INSERT INTO `sys_dict_type` VALUES (6, '系统是否', 'sys_yes_no', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统是否列表');
INSERT INTO `sys_dict_type` VALUES (7, '通知类型', 'sys_notice_type', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知类型列表');
INSERT INTO `sys_dict_type` VALUES (8, '通知状态', 'sys_notice_status', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知状态列表');
INSERT INTO `sys_dict_type` VALUES (9, '操作类型', 'sys_oper_type', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '操作类型列表');
INSERT INTO `sys_dict_type` VALUES (10, '系统状态', 'sys_common_status', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '登录状态列表');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int(4) DEFAULT 0 COMMENT '显示顺序',
  `path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '组件路径',
  `is_frame` int(1) DEFAULT 1 COMMENT '是否为外链（0是 1否）',
  `menu_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1233 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单权限表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, 10, 'system', NULL, 1, 'M', '0', '0', '', 'system', 'admin', '2018-03-16 11:33:00', 'admin', '2020-08-13 13:31:50', '系统管理目录');
INSERT INTO `sys_menu` VALUES (100, '用户管理', 1, 1, 'user', 'system/user/index', 1, 'C', '0', '0', 'system:user:list', 'user', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '用户管理菜单');
INSERT INTO `sys_menu` VALUES (101, '角色管理', 1, 2, 'role', 'system/role/index', 1, 'C', '0', '0', 'system:role:list', 'peoples', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '角色管理菜单');
INSERT INTO `sys_menu` VALUES (102, '菜单管理', 1, 3, 'menu', 'system/menu/index', 1, 'C', '0', '0', 'system:menu:list', 'tree-table', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '菜单管理菜单');
INSERT INTO `sys_menu` VALUES (103, '部门管理', 1, 4, 'dept', 'system/dept/index', 1, 'C', '0', '0', 'system:dept:list', 'tree', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '部门管理菜单');
INSERT INTO `sys_menu` VALUES (104, '岗位管理', 1, 5, 'post', 'system/post/index', 1, 'C', '0', '0', 'system:post:list', 'post', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '岗位管理菜单');
INSERT INTO `sys_menu` VALUES (105, '字典管理', 1, 6, 'dict', 'system/dict/index', 1, 'C', '0', '0', 'system:dict:list', 'dict', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '字典管理菜单');
INSERT INTO `sys_menu` VALUES (1001, '用户查询', 100, 1, '', '', 1, 'F', '0', '0', 'system:user:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1002, '用户新增', 100, 2, '', '', 1, 'F', '0', '0', 'system:user:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1003, '用户修改', 100, 3, '', '', 1, 'F', '0', '0', 'system:user:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1004, '用户删除', 100, 4, '', '', 1, 'F', '0', '0', 'system:user:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1007, '重置密码', 100, 7, '', '', 1, 'F', '0', '0', 'system:user:resetPwd', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1008, '角色查询', 101, 1, '', '', 1, 'F', '0', '0', 'system:role:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1009, '角色新增', 101, 2, '', '', 1, 'F', '0', '0', 'system:role:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1010, '角色修改', 101, 3, '', '', 1, 'F', '0', '0', 'system:role:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1011, '角色删除', 101, 4, '', '', 1, 'F', '0', '0', 'system:role:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1013, '菜单查询', 102, 1, '', '', 1, 'F', '0', '0', 'system:menu:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1014, '菜单新增', 102, 2, '', '', 1, 'F', '0', '0', 'system:menu:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1015, '菜单修改', 102, 3, '', '', 1, 'F', '0', '0', 'system:menu:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1016, '菜单删除', 102, 4, '', '', 1, 'F', '0', '0', 'system:menu:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1017, '部门查询', 103, 1, '', '', 1, 'F', '0', '0', 'system:dept:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1018, '部门新增', 103, 2, '', '', 1, 'F', '0', '0', 'system:dept:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1019, '部门修改', 103, 3, '', '', 1, 'F', '0', '0', 'system:dept:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1020, '部门删除', 103, 4, '', '', 1, 'F', '0', '0', 'system:dept:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1021, '岗位查询', 104, 1, '', '', 1, 'F', '0', '0', 'system:post:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1022, '岗位新增', 104, 2, '', '', 1, 'F', '0', '0', 'system:post:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1023, '岗位修改', 104, 3, '', '', 1, 'F', '0', '0', 'system:post:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1024, '岗位删除', 104, 4, '', '', 1, 'F', '0', '0', 'system:post:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1026, '字典查询', 105, 1, '#', '', 1, 'F', '0', '0', 'system:dict:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1027, '字典新增', 105, 2, '#', '', 1, 'F', '0', '0', 'system:dict:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1028, '字典修改', 105, 3, '#', '', 1, 'F', '0', '0', 'system:dict:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1029, '字典删除', 105, 4, '#', '', 1, 'F', '0', '0', 'system:dict:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`  (
  `post_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '岗位名称',
  `post_sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '岗位信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES (1, '管理员', 'admin', 1, '0', 'admin', '2018-03-16 11:33:00', 'admin', '2020-09-03 10:11:50', '');
INSERT INTO `sys_post` VALUES (8, '2', '测试', 2, '0', '', '2020-09-03 10:14:05', '', NULL, NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '系统管理员', 'admin', 1, '1', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统管理员');
INSERT INTO `sys_role` VALUES (2, '普通角色', 'common', 2, '2', '0', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2020-08-20 09:33:15', '普通角色');
INSERT INTO `sys_role` VALUES (7, '测试', 'test', 3, '1', '0', '0', '', '2020-09-03 10:11:12', '', NULL, '测试用');

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`, `dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色和部门关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES (2, 100);
INSERT INTO `sys_role_dept` VALUES (2, 101);
INSERT INTO `sys_role_dept` VALUES (2, 105);
INSERT INTO `sys_role_dept` VALUES (3, 100);
INSERT INTO `sys_role_dept` VALUES (3, 101);
INSERT INTO `sys_role_dept` VALUES (3, 102);
INSERT INTO `sys_role_dept` VALUES (3, 103);
INSERT INTO `sys_role_dept` VALUES (3, 104);
INSERT INTO `sys_role_dept` VALUES (3, 108);
INSERT INTO `sys_role_dept` VALUES (3, 110);
INSERT INTO `sys_role_dept` VALUES (3, 111);
INSERT INTO `sys_role_dept` VALUES (3, 112);
INSERT INTO `sys_role_dept` VALUES (3, 113);
INSERT INTO `sys_role_dept` VALUES (3, 114);
INSERT INTO `sys_role_dept` VALUES (3, 115);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (2, 1061);
INSERT INTO `sys_role_menu` VALUES (2, 1062);
INSERT INTO `sys_role_menu` VALUES (2, 1063);
INSERT INTO `sys_role_menu` VALUES (2, 1064);
INSERT INTO `sys_role_menu` VALUES (2, 1065);
INSERT INTO `sys_role_menu` VALUES (2, 1069);
INSERT INTO `sys_role_menu` VALUES (2, 1070);
INSERT INTO `sys_role_menu` VALUES (2, 1071);
INSERT INTO `sys_role_menu` VALUES (2, 1072);
INSERT INTO `sys_role_menu` VALUES (2, 1073);
INSERT INTO `sys_role_menu` VALUES (2, 1074);
INSERT INTO `sys_role_menu` VALUES (2, 1075);
INSERT INTO `sys_role_menu` VALUES (3, 1061);
INSERT INTO `sys_role_menu` VALUES (3, 1062);
INSERT INTO `sys_role_menu` VALUES (3, 1063);
INSERT INTO `sys_role_menu` VALUES (3, 1064);
INSERT INTO `sys_role_menu` VALUES (3, 1065);
INSERT INTO `sys_role_menu` VALUES (3, 1066);
INSERT INTO `sys_role_menu` VALUES (3, 1067);
INSERT INTO `sys_role_menu` VALUES (3, 1068);
INSERT INTO `sys_role_menu` VALUES (3, 1069);
INSERT INTO `sys_role_menu` VALUES (3, 1070);
INSERT INTO `sys_role_menu` VALUES (3, 1071);
INSERT INTO `sys_role_menu` VALUES (3, 1072);
INSERT INTO `sys_role_menu` VALUES (3, 1073);
INSERT INTO `sys_role_menu` VALUES (3, 1074);
INSERT INTO `sys_role_menu` VALUES (3, 1075);
INSERT INTO `sys_role_menu` VALUES (4, 1);
INSERT INTO `sys_role_menu` VALUES (4, 2);
INSERT INTO `sys_role_menu` VALUES (4, 100);
INSERT INTO `sys_role_menu` VALUES (4, 101);
INSERT INTO `sys_role_menu` VALUES (4, 102);
INSERT INTO `sys_role_menu` VALUES (4, 103);
INSERT INTO `sys_role_menu` VALUES (4, 104);
INSERT INTO `sys_role_menu` VALUES (4, 105);
INSERT INTO `sys_role_menu` VALUES (4, 106);
INSERT INTO `sys_role_menu` VALUES (4, 107);
INSERT INTO `sys_role_menu` VALUES (4, 108);
INSERT INTO `sys_role_menu` VALUES (4, 109);
INSERT INTO `sys_role_menu` VALUES (4, 110);
INSERT INTO `sys_role_menu` VALUES (4, 111);
INSERT INTO `sys_role_menu` VALUES (4, 112);
INSERT INTO `sys_role_menu` VALUES (4, 500);
INSERT INTO `sys_role_menu` VALUES (4, 501);
INSERT INTO `sys_role_menu` VALUES (4, 1001);
INSERT INTO `sys_role_menu` VALUES (4, 1002);
INSERT INTO `sys_role_menu` VALUES (4, 1003);
INSERT INTO `sys_role_menu` VALUES (4, 1004);
INSERT INTO `sys_role_menu` VALUES (4, 1007);
INSERT INTO `sys_role_menu` VALUES (4, 1008);
INSERT INTO `sys_role_menu` VALUES (4, 1009);
INSERT INTO `sys_role_menu` VALUES (4, 1010);
INSERT INTO `sys_role_menu` VALUES (4, 1011);
INSERT INTO `sys_role_menu` VALUES (4, 1013);
INSERT INTO `sys_role_menu` VALUES (4, 1014);
INSERT INTO `sys_role_menu` VALUES (4, 1015);
INSERT INTO `sys_role_menu` VALUES (4, 1016);
INSERT INTO `sys_role_menu` VALUES (4, 1017);
INSERT INTO `sys_role_menu` VALUES (4, 1018);
INSERT INTO `sys_role_menu` VALUES (4, 1019);
INSERT INTO `sys_role_menu` VALUES (4, 1020);
INSERT INTO `sys_role_menu` VALUES (4, 1021);
INSERT INTO `sys_role_menu` VALUES (4, 1022);
INSERT INTO `sys_role_menu` VALUES (4, 1023);
INSERT INTO `sys_role_menu` VALUES (4, 1024);
INSERT INTO `sys_role_menu` VALUES (4, 1026);
INSERT INTO `sys_role_menu` VALUES (4, 1027);
INSERT INTO `sys_role_menu` VALUES (4, 1028);
INSERT INTO `sys_role_menu` VALUES (4, 1029);
INSERT INTO `sys_role_menu` VALUES (4, 1031);
INSERT INTO `sys_role_menu` VALUES (4, 1032);
INSERT INTO `sys_role_menu` VALUES (4, 1033);
INSERT INTO `sys_role_menu` VALUES (4, 1034);
INSERT INTO `sys_role_menu` VALUES (4, 1035);
INSERT INTO `sys_role_menu` VALUES (4, 1036);
INSERT INTO `sys_role_menu` VALUES (4, 1037);
INSERT INTO `sys_role_menu` VALUES (4, 1038);
INSERT INTO `sys_role_menu` VALUES (4, 1039);
INSERT INTO `sys_role_menu` VALUES (4, 1040);
INSERT INTO `sys_role_menu` VALUES (4, 1041);
INSERT INTO `sys_role_menu` VALUES (4, 1042);
INSERT INTO `sys_role_menu` VALUES (4, 1043);
INSERT INTO `sys_role_menu` VALUES (4, 1044);
INSERT INTO `sys_role_menu` VALUES (4, 1045);
INSERT INTO `sys_role_menu` VALUES (4, 1046);
INSERT INTO `sys_role_menu` VALUES (4, 1047);
INSERT INTO `sys_role_menu` VALUES (4, 1048);
INSERT INTO `sys_role_menu` VALUES (4, 1049);
INSERT INTO `sys_role_menu` VALUES (4, 1050);
INSERT INTO `sys_role_menu` VALUES (4, 1051);
INSERT INTO `sys_role_menu` VALUES (4, 1052);
INSERT INTO `sys_role_menu` VALUES (4, 1053);
INSERT INTO `sys_role_menu` VALUES (4, 1054);
INSERT INTO `sys_role_menu` VALUES (4, 1061);
INSERT INTO `sys_role_menu` VALUES (4, 1062);
INSERT INTO `sys_role_menu` VALUES (4, 1063);
INSERT INTO `sys_role_menu` VALUES (4, 1064);
INSERT INTO `sys_role_menu` VALUES (4, 1065);
INSERT INTO `sys_role_menu` VALUES (4, 1066);
INSERT INTO `sys_role_menu` VALUES (4, 1067);
INSERT INTO `sys_role_menu` VALUES (4, 1068);
INSERT INTO `sys_role_menu` VALUES (4, 1069);
INSERT INTO `sys_role_menu` VALUES (4, 1070);
INSERT INTO `sys_role_menu` VALUES (4, 1071);
INSERT INTO `sys_role_menu` VALUES (4, 1072);
INSERT INTO `sys_role_menu` VALUES (4, 1073);
INSERT INTO `sys_role_menu` VALUES (4, 1074);
INSERT INTO `sys_role_menu` VALUES (4, 1075);
INSERT INTO `sys_role_menu` VALUES (4, 1076);
INSERT INTO `sys_role_menu` VALUES (4, 1077);
INSERT INTO `sys_role_menu` VALUES (4, 1078);
INSERT INTO `sys_role_menu` VALUES (4, 1079);
INSERT INTO `sys_role_menu` VALUES (4, 1080);
INSERT INTO `sys_role_menu` VALUES (4, 1081);
INSERT INTO `sys_role_menu` VALUES (4, 1082);
INSERT INTO `sys_role_menu` VALUES (4, 1083);
INSERT INTO `sys_role_menu` VALUES (4, 1084);
INSERT INTO `sys_role_menu` VALUES (4, 1085);
INSERT INTO `sys_role_menu` VALUES (4, 1086);
INSERT INTO `sys_role_menu` VALUES (4, 1087);
INSERT INTO `sys_role_menu` VALUES (4, 1088);
INSERT INTO `sys_role_menu` VALUES (4, 1089);
INSERT INTO `sys_role_menu` VALUES (4, 1090);
INSERT INTO `sys_role_menu` VALUES (4, 1091);
INSERT INTO `sys_role_menu` VALUES (4, 1092);
INSERT INTO `sys_role_menu` VALUES (4, 1093);
INSERT INTO `sys_role_menu` VALUES (4, 1094);
INSERT INTO `sys_role_menu` VALUES (4, 1095);
INSERT INTO `sys_role_menu` VALUES (4, 1096);
INSERT INTO `sys_role_menu` VALUES (4, 1097);
INSERT INTO `sys_role_menu` VALUES (4, 1098);
INSERT INTO `sys_role_menu` VALUES (4, 1099);
INSERT INTO `sys_role_menu` VALUES (4, 1100);
INSERT INTO `sys_role_menu` VALUES (4, 1101);
INSERT INTO `sys_role_menu` VALUES (4, 1102);
INSERT INTO `sys_role_menu` VALUES (4, 1103);
INSERT INTO `sys_role_menu` VALUES (4, 1104);
INSERT INTO `sys_role_menu` VALUES (4, 1105);
INSERT INTO `sys_role_menu` VALUES (4, 1106);
INSERT INTO `sys_role_menu` VALUES (4, 1107);
INSERT INTO `sys_role_menu` VALUES (4, 1108);
INSERT INTO `sys_role_menu` VALUES (4, 1109);
INSERT INTO `sys_role_menu` VALUES (4, 1110);
INSERT INTO `sys_role_menu` VALUES (4, 1111);
INSERT INTO `sys_role_menu` VALUES (4, 1112);
INSERT INTO `sys_role_menu` VALUES (4, 1113);
INSERT INTO `sys_role_menu` VALUES (4, 1114);
INSERT INTO `sys_role_menu` VALUES (4, 1115);
INSERT INTO `sys_role_menu` VALUES (4, 1116);
INSERT INTO `sys_role_menu` VALUES (4, 1117);
INSERT INTO `sys_role_menu` VALUES (4, 1118);
INSERT INTO `sys_role_menu` VALUES (4, 1119);
INSERT INTO `sys_role_menu` VALUES (4, 1120);
INSERT INTO `sys_role_menu` VALUES (4, 1121);
INSERT INTO `sys_role_menu` VALUES (4, 1122);
INSERT INTO `sys_role_menu` VALUES (4, 1123);
INSERT INTO `sys_role_menu` VALUES (4, 1124);
INSERT INTO `sys_role_menu` VALUES (4, 1125);
INSERT INTO `sys_role_menu` VALUES (4, 1126);
INSERT INTO `sys_role_menu` VALUES (4, 1127);
INSERT INTO `sys_role_menu` VALUES (4, 1128);
INSERT INTO `sys_role_menu` VALUES (4, 1129);
INSERT INTO `sys_role_menu` VALUES (4, 1130);
INSERT INTO `sys_role_menu` VALUES (4, 1131);
INSERT INTO `sys_role_menu` VALUES (4, 1132);
INSERT INTO `sys_role_menu` VALUES (4, 1133);
INSERT INTO `sys_role_menu` VALUES (4, 1134);
INSERT INTO `sys_role_menu` VALUES (4, 1135);
INSERT INTO `sys_role_menu` VALUES (4, 1136);
INSERT INTO `sys_role_menu` VALUES (4, 1137);
INSERT INTO `sys_role_menu` VALUES (4, 1138);
INSERT INTO `sys_role_menu` VALUES (4, 1139);
INSERT INTO `sys_role_menu` VALUES (4, 1140);
INSERT INTO `sys_role_menu` VALUES (4, 1141);
INSERT INTO `sys_role_menu` VALUES (4, 1142);
INSERT INTO `sys_role_menu` VALUES (4, 1143);
INSERT INTO `sys_role_menu` VALUES (4, 1146);
INSERT INTO `sys_role_menu` VALUES (4, 1147);
INSERT INTO `sys_role_menu` VALUES (4, 1148);
INSERT INTO `sys_role_menu` VALUES (4, 1149);
INSERT INTO `sys_role_menu` VALUES (4, 1150);
INSERT INTO `sys_role_menu` VALUES (4, 1151);
INSERT INTO `sys_role_menu` VALUES (4, 1152);
INSERT INTO `sys_role_menu` VALUES (4, 1153);
INSERT INTO `sys_role_menu` VALUES (4, 1154);
INSERT INTO `sys_role_menu` VALUES (4, 1155);
INSERT INTO `sys_role_menu` VALUES (4, 1156);
INSERT INTO `sys_role_menu` VALUES (4, 1157);
INSERT INTO `sys_role_menu` VALUES (4, 1158);
INSERT INTO `sys_role_menu` VALUES (4, 1159);
INSERT INTO `sys_role_menu` VALUES (4, 1160);
INSERT INTO `sys_role_menu` VALUES (4, 1161);
INSERT INTO `sys_role_menu` VALUES (4, 1162);
INSERT INTO `sys_role_menu` VALUES (4, 1163);
INSERT INTO `sys_role_menu` VALUES (4, 1164);
INSERT INTO `sys_role_menu` VALUES (4, 1165);
INSERT INTO `sys_role_menu` VALUES (4, 1166);
INSERT INTO `sys_role_menu` VALUES (4, 1167);
INSERT INTO `sys_role_menu` VALUES (4, 1168);
INSERT INTO `sys_role_menu` VALUES (4, 1169);
INSERT INTO `sys_role_menu` VALUES (4, 1170);
INSERT INTO `sys_role_menu` VALUES (4, 1171);
INSERT INTO `sys_role_menu` VALUES (4, 1172);
INSERT INTO `sys_role_menu` VALUES (4, 1173);
INSERT INTO `sys_role_menu` VALUES (4, 1174);
INSERT INTO `sys_role_menu` VALUES (4, 1175);
INSERT INTO `sys_role_menu` VALUES (4, 1176);
INSERT INTO `sys_role_menu` VALUES (4, 1177);
INSERT INTO `sys_role_menu` VALUES (4, 1178);
INSERT INTO `sys_role_menu` VALUES (4, 1179);
INSERT INTO `sys_role_menu` VALUES (4, 1180);
INSERT INTO `sys_role_menu` VALUES (4, 1181);
INSERT INTO `sys_role_menu` VALUES (4, 1182);
INSERT INTO `sys_role_menu` VALUES (4, 1183);
INSERT INTO `sys_role_menu` VALUES (4, 1184);
INSERT INTO `sys_role_menu` VALUES (4, 1185);
INSERT INTO `sys_role_menu` VALUES (4, 1186);
INSERT INTO `sys_role_menu` VALUES (4, 1187);
INSERT INTO `sys_role_menu` VALUES (4, 1188);
INSERT INTO `sys_role_menu` VALUES (4, 1189);
INSERT INTO `sys_role_menu` VALUES (4, 1190);
INSERT INTO `sys_role_menu` VALUES (4, 1191);
INSERT INTO `sys_role_menu` VALUES (4, 1192);
INSERT INTO `sys_role_menu` VALUES (4, 1193);
INSERT INTO `sys_role_menu` VALUES (4, 1194);
INSERT INTO `sys_role_menu` VALUES (4, 1195);
INSERT INTO `sys_role_menu` VALUES (4, 1196);
INSERT INTO `sys_role_menu` VALUES (4, 1197);
INSERT INTO `sys_role_menu` VALUES (4, 1198);
INSERT INTO `sys_role_menu` VALUES (4, 1199);
INSERT INTO `sys_role_menu` VALUES (4, 1200);
INSERT INTO `sys_role_menu` VALUES (5, 1061);
INSERT INTO `sys_role_menu` VALUES (5, 1062);
INSERT INTO `sys_role_menu` VALUES (5, 1063);
INSERT INTO `sys_role_menu` VALUES (5, 1064);
INSERT INTO `sys_role_menu` VALUES (5, 1065);
INSERT INTO `sys_role_menu` VALUES (5, 1066);
INSERT INTO `sys_role_menu` VALUES (5, 1067);
INSERT INTO `sys_role_menu` VALUES (5, 1068);
INSERT INTO `sys_role_menu` VALUES (5, 1069);
INSERT INTO `sys_role_menu` VALUES (5, 1070);
INSERT INTO `sys_role_menu` VALUES (5, 1071);
INSERT INTO `sys_role_menu` VALUES (5, 1072);
INSERT INTO `sys_role_menu` VALUES (5, 1073);
INSERT INTO `sys_role_menu` VALUES (5, 1074);
INSERT INTO `sys_role_menu` VALUES (5, 1075);
INSERT INTO `sys_role_menu` VALUES (5, 1076);
INSERT INTO `sys_role_menu` VALUES (5, 1077);
INSERT INTO `sys_role_menu` VALUES (5, 1078);
INSERT INTO `sys_role_menu` VALUES (5, 1079);
INSERT INTO `sys_role_menu` VALUES (5, 1080);
INSERT INTO `sys_role_menu` VALUES (5, 1081);
INSERT INTO `sys_role_menu` VALUES (5, 1082);
INSERT INTO `sys_role_menu` VALUES (5, 1083);
INSERT INTO `sys_role_menu` VALUES (5, 1084);
INSERT INTO `sys_role_menu` VALUES (5, 1085);
INSERT INTO `sys_role_menu` VALUES (5, 1086);
INSERT INTO `sys_role_menu` VALUES (5, 1087);
INSERT INTO `sys_role_menu` VALUES (5, 1088);
INSERT INTO `sys_role_menu` VALUES (5, 1089);
INSERT INTO `sys_role_menu` VALUES (5, 1090);
INSERT INTO `sys_role_menu` VALUES (5, 1091);
INSERT INTO `sys_role_menu` VALUES (5, 1092);
INSERT INTO `sys_role_menu` VALUES (5, 1094);
INSERT INTO `sys_role_menu` VALUES (5, 1095);
INSERT INTO `sys_role_menu` VALUES (5, 1096);
INSERT INTO `sys_role_menu` VALUES (5, 1100);
INSERT INTO `sys_role_menu` VALUES (5, 1101);
INSERT INTO `sys_role_menu` VALUES (5, 1102);
INSERT INTO `sys_role_menu` VALUES (5, 1103);
INSERT INTO `sys_role_menu` VALUES (5, 1104);
INSERT INTO `sys_role_menu` VALUES (5, 1105);
INSERT INTO `sys_role_menu` VALUES (5, 1106);
INSERT INTO `sys_role_menu` VALUES (5, 1107);
INSERT INTO `sys_role_menu` VALUES (5, 1108);
INSERT INTO `sys_role_menu` VALUES (5, 1109);
INSERT INTO `sys_role_menu` VALUES (5, 1110);
INSERT INTO `sys_role_menu` VALUES (5, 1111);
INSERT INTO `sys_role_menu` VALUES (5, 1112);
INSERT INTO `sys_role_menu` VALUES (5, 1113);
INSERT INTO `sys_role_menu` VALUES (5, 1114);
INSERT INTO `sys_role_menu` VALUES (5, 1115);
INSERT INTO `sys_role_menu` VALUES (5, 1116);
INSERT INTO `sys_role_menu` VALUES (5, 1117);
INSERT INTO `sys_role_menu` VALUES (5, 1118);
INSERT INTO `sys_role_menu` VALUES (5, 1119);
INSERT INTO `sys_role_menu` VALUES (5, 1120);
INSERT INTO `sys_role_menu` VALUES (5, 1121);
INSERT INTO `sys_role_menu` VALUES (5, 1122);
INSERT INTO `sys_role_menu` VALUES (5, 1123);
INSERT INTO `sys_role_menu` VALUES (5, 1124);
INSERT INTO `sys_role_menu` VALUES (5, 1125);
INSERT INTO `sys_role_menu` VALUES (5, 1126);
INSERT INTO `sys_role_menu` VALUES (5, 1127);
INSERT INTO `sys_role_menu` VALUES (5, 1128);
INSERT INTO `sys_role_menu` VALUES (5, 1129);
INSERT INTO `sys_role_menu` VALUES (5, 1130);
INSERT INTO `sys_role_menu` VALUES (5, 1131);
INSERT INTO `sys_role_menu` VALUES (5, 1132);
INSERT INTO `sys_role_menu` VALUES (5, 1133);
INSERT INTO `sys_role_menu` VALUES (5, 1134);
INSERT INTO `sys_role_menu` VALUES (5, 1135);
INSERT INTO `sys_role_menu` VALUES (5, 1136);
INSERT INTO `sys_role_menu` VALUES (5, 1137);
INSERT INTO `sys_role_menu` VALUES (5, 1138);
INSERT INTO `sys_role_menu` VALUES (5, 1139);
INSERT INTO `sys_role_menu` VALUES (5, 1140);
INSERT INTO `sys_role_menu` VALUES (5, 1141);
INSERT INTO `sys_role_menu` VALUES (5, 1142);
INSERT INTO `sys_role_menu` VALUES (5, 1143);
INSERT INTO `sys_role_menu` VALUES (5, 1146);
INSERT INTO `sys_role_menu` VALUES (5, 1147);
INSERT INTO `sys_role_menu` VALUES (5, 1148);
INSERT INTO `sys_role_menu` VALUES (5, 1149);
INSERT INTO `sys_role_menu` VALUES (5, 1150);
INSERT INTO `sys_role_menu` VALUES (5, 1151);
INSERT INTO `sys_role_menu` VALUES (5, 1152);
INSERT INTO `sys_role_menu` VALUES (5, 1153);
INSERT INTO `sys_role_menu` VALUES (5, 1154);
INSERT INTO `sys_role_menu` VALUES (5, 1155);
INSERT INTO `sys_role_menu` VALUES (5, 1156);
INSERT INTO `sys_role_menu` VALUES (5, 1157);
INSERT INTO `sys_role_menu` VALUES (5, 1158);
INSERT INTO `sys_role_menu` VALUES (5, 1159);
INSERT INTO `sys_role_menu` VALUES (5, 1160);
INSERT INTO `sys_role_menu` VALUES (5, 1161);
INSERT INTO `sys_role_menu` VALUES (5, 1162);
INSERT INTO `sys_role_menu` VALUES (5, 1163);
INSERT INTO `sys_role_menu` VALUES (5, 1164);
INSERT INTO `sys_role_menu` VALUES (5, 1165);
INSERT INTO `sys_role_menu` VALUES (5, 1166);
INSERT INTO `sys_role_menu` VALUES (5, 1167);
INSERT INTO `sys_role_menu` VALUES (5, 1168);
INSERT INTO `sys_role_menu` VALUES (5, 1169);
INSERT INTO `sys_role_menu` VALUES (5, 1170);
INSERT INTO `sys_role_menu` VALUES (5, 1171);
INSERT INTO `sys_role_menu` VALUES (5, 1172);
INSERT INTO `sys_role_menu` VALUES (5, 1173);
INSERT INTO `sys_role_menu` VALUES (7, 1);
INSERT INTO `sys_role_menu` VALUES (7, 100);
INSERT INTO `sys_role_menu` VALUES (7, 101);
INSERT INTO `sys_role_menu` VALUES (7, 102);
INSERT INTO `sys_role_menu` VALUES (7, 103);
INSERT INTO `sys_role_menu` VALUES (7, 104);
INSERT INTO `sys_role_menu` VALUES (7, 105);
INSERT INTO `sys_role_menu` VALUES (7, 106);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `staff_id` int(11) DEFAULT NULL COMMENT '员工id',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '手机号码',
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '密码',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '最后登陆IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, NULL, 103, 'admin', '超级管理员', '00', 'ry@163.com', '15888888888', '1', 'http://192.168.1.13:8999/zhzh/路径 48@2x.png', 'e10adc3949ba59abbe56e057f20f883e', '0', '0', '127.0.0.1', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '管理员');
INSERT INTO `sys_user` VALUES (2, NULL, 103, 'ry', '超级管理员', '00', 'ry@qq.com', '15666666666', '1', '', 'e10adc3949ba59abbe56e057f20f883e', '0', '0', '127.0.0.1', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', 'ry', '2020-08-26 14:08:29', '测试员');
INSERT INTO `sys_user` VALUES (11, NULL, 119, 'test', '测试', '00', '8963@qq.com', '18778909876', '0', '', 'e10adc3949ba59abbe56e057f20f883e', '0', '0', '', NULL, '', '2020-09-03 10:15:59', '', '2020-09-03 10:16:18', '测试111');

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`, `post_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户与岗位关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post` VALUES (1, 1);
INSERT INTO `sys_user_post` VALUES (2, 2);
INSERT INTO `sys_user_post` VALUES (5, 3);
INSERT INTO `sys_user_post` VALUES (6, 5);
INSERT INTO `sys_user_post` VALUES (9, 4);
INSERT INTO `sys_user_post` VALUES (10, 2);
INSERT INTO `sys_user_post` VALUES (11, 1);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户和角色关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);
INSERT INTO `sys_user_role` VALUES (11, 2);

-- ----------------------------
-- Event structure for update_days
-- ----------------------------
-- DROP EVENT IF EXISTS `update_days`;
-- delimiter ;;
-- CREATE DEFINER = `root`@`localhost` EVENT `update_days`
-- ON SCHEDULE
-- EVERY '1' DAY STARTS '2020-08-01 23:30:00'
-- ON COMPLETION PRESERVE
-- DISABLE
-- DO UPDATE change_record
-- SET days_in_month = days_in_month + 1 ,
-- 		money= after_change_money*days_in_month/(select DATEDIFF(date_add(curdate()-day(curdate())+1,interval 1 month ),DATE_ADD(curdate(),interval -day(curdate())+1 day)) from dual)
-- WHERE
-- 	id IN ( SELECT a.id FROM ( SELECT * FROM change_record WHERE TO_DAYS( work_date ) <= to_days( now( ) ) ) AS a )
-- 	AND update_state = 1
-- 	and is_del=1
-- ;;
-- delimiter ;

-- ----------------------------
-- Event structure for update_month
-- ----------------------------
-- DROP EVENT IF EXISTS `update_month`;
-- delimiter ;;
-- CREATE DEFINER = `root`@`localhost` EVENT `update_month`
-- ON SCHEDULE
-- EVERY '1' MONTH STARTS '2020-08-01 22:00:00'
-- ON COMPLETION PRESERVE
-- DO UPDATE change_record
-- SET days_in_month=0,
-- money=0
-- ;;
-- delimiter ;
--
-- SET FOREIGN_KEY_CHECKS = 1;
