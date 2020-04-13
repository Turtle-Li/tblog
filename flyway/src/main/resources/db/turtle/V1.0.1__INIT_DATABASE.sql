CREATE TABLE IF NOT EXISTS `tbg_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) DEFAULT NULL COMMENT '用户名',
  `name` varchar(32) DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(128) DEFAULT NULL COMMENT '头像',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `status` int(5) NOT NULL COMMENT '用户状态',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `last_login` datetime DEFAULT NULL COMMENT '最后登录时间',
  `gender` int(5) NOT NULL COMMENT '性别',
  `comments` int(11) NOT NULL COMMENT '评论数',
  `posts` int(11) NOT NULL COMMENT '文章数',
  `signature` varchar(140) DEFAULT NULL COMMENT '个性签名',
  `is_delete` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `UK_USERNAME` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户表' ;

CREATE TABLE IF NOT EXISTS `tbg_options` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `key` varchar(32) DEFAULT NULL ,
  `value` varchar(300) DEFAULT NULL ,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT '配置表';