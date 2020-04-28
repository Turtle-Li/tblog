CREATE TABLE IF NOT EXISTS `tbg_user` (
  `id` bigint(20) NOT NULL,
  `user_name` varchar(32) DEFAULT NULL COMMENT '用户名',
  `name` varchar(32) DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(128) DEFAULT NULL COMMENT '头像',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `status` int(5) NOT NULL COMMENT '用户状态',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `last_login` datetime DEFAULT NULL COMMENT '最后登录时间',
  `gender` int(5) DEFAULT NULL COMMENT '性别',
  `comments` int(11) NOT NULL DEFAULT '0' COMMENT '评论数',
  `posts` int(11) NOT NULL DEFAULT '0' COMMENT '文章数',
  `signature` varchar(140) DEFAULT NULL COMMENT '个性签名',
  `is_delete` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户表';

CREATE TABLE IF NOT EXISTS `tbg_options` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `key` varchar(32) DEFAULT NULL ,
  `value` varchar(300) DEFAULT NULL ,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT '配置表';

CREATE TABLE IF NOT EXISTS `tbg_role` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `role_name` varchar(255) DEFAULT NULL COMMENT '角色标志',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '角色名称',
  `group_id` bigint(20) DEFAULT NULL COMMENT '组id',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_delete` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色表';

CREATE TABLE IF NOT EXISTS `tbg_role_group` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `name` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '组名称',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_delete` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT '角色组表';

CREATE TABLE IF NOT EXISTS `tbg_user_role` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT '用户角色关系表';
