13917097762

18999887766

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_name` varchar(500) NOT NULL COMMENT '用户名称',
  `real_name` varchar(500) DEFAULT NULL COMMENT '真实用户名称',
  `address` varchar(1000) DEFAULT NULL COMMENT '住址',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `sex` int(1) DEFAULT NULL COMMENT '姓别',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `userpwd` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(100) DEFAULT NULL COMMENT '加密盐值',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` int(11) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_by` int(11) DEFAULT NULL COMMENT '修改人',
  `status` int(1) DEFAULT '0' COMMENT '是否有效',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `index_user_userName` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户信息';

CREATE TABLE `roles` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_name` varchar(500) NOT NULL COMMENT '角色名称',
  `descript` varchar(500) DEFAULT NULL COMMENT '角色描述',
  `status` int(3) DEFAULT '0' COMMENT '是否有效',
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `index_roles_name` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色信息';

CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关系';

CREATE TABLE `permissions` (
  `perm_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `perm_name` varchar(500) NOT NULL COMMENT '权限名称',
  `desc` varchar(500) DEFAULT NULL COMMENT '权限描述',
  `status` int(1) DEFAULT '0' COMMENT '是否有效',
  PRIMARY KEY (`perm_id`),
  UNIQUE KEY `perm_name` (`perm_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限信息';

CREATE TABLE `role_perm` (
  `perm_id` int(11) NOT NULL COMMENT '权限ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`perm_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限关系';
