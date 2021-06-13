INSERT INTO hr.certificate_flow (id, type, pre_id, user_name, apply_date, cert_no, cert_name, cert_date, approver_id, approve_date, approve_result, state)
VALUES ('0e70da1fc9d811ea9fa9000c29ee2ecd', 0, NULL, 'admin', '2020-07-19 15:53:28', '123', '234', '2020-07-18 16:00:00', 'admin', NULL, 0, 1);

INSERT INTO hr.certificate_flow (id, type, pre_id, user_name, apply_date, cert_no, cert_name, cert_date, approver_id, approve_date, approve_result, state)
VALUES ('119fc0e3ca9911ea9fa9000c29ee2ecd', 1, 'f1bf05cfca9811ea9fa9000c29ee2ecd', 'admin', '2020-07-20 14:55:06', 'hseconomy-1234', '高级经济师', '2020-07-19 16:00:00', 'admin', NULL, 0, 1);

INSERT INTO hr.certificate_flow (id, type, pre_id, user_name, apply_date, cert_no, cert_name, cert_date, approver_id, approve_date, approve_result, state)
VALUES ('22977bdfc9d811ea9fa9000c29ee2ecd', 1, 'ffbed786c96b11ea9fa9000c29ee2ecd', 'admin', '2020-07-19 15:54:02', '1234', '高级工程师', '2020-07-19 16:00:00', 'admin', NULL, 0, 1);

INSERT INTO hr.certificate_flow (id, type, pre_id, user_name, apply_date, cert_no, cert_name, cert_date, approver_id, approve_date, approve_result, state)
VALUES ('27fe13c6ca9911ea9fa9000c29ee2ecd', 1, 'ffbed786c96b11ea9fa9000c29ee2ecd', 'admin', '2020-07-20 14:55:43', 'hiengineer-12345678', '高级工程师', '2020-07-19 16:00:00', 'admin', NULL, 1, 1);

INSERT INTO hr.certificate_flow (id, type, pre_id, user_name, apply_date, cert_no, cert_name, cert_date, approver_id, approve_date, approve_result, state)
VALUES ('2fd6e19eca4011ea9fa9000c29ee2ecd', 0, NULL, 'user', '2020-07-20 04:18:52', 'user-cert-no', 'user-cert-name', '2020-07-19 16:00:00', 'admin', NULL, 0, 1);

INSERT INTO hr.certificate_flow (id, type, pre_id, user_name, apply_date, cert_no, cert_name, cert_date, approver_id, approve_date, approve_result, state)
VALUES ('4ea8a025ca9911ea9fa9000c29ee2ecd', 1, 'ffbed786c96b11ea9fa9000c29ee2ecd', 'admin', '2020-07-20 14:56:48', 'hskuaiji', '高级会计师', '2020-07-19 16:00:00', 'admin', NULL, 0, 1);

INSERT INTO hr.certificate_flow (id, type, pre_id, user_name, apply_date, cert_no, cert_name, cert_date, approver_id, approve_date, approve_result, state)
VALUES ('5a9b790aca9911ea9fa9000c29ee2ecd', 1, 'ffbed786c96b11ea9fa9000c29ee2ecd', 'admin', '2020-07-20 14:57:08', 'hskuaiji-1234', '高级会计师', '2020-07-19 16:00:00', 'admin', NULL, 0, 1);

INSERT INTO hr.certificate_flow (id, type, pre_id, user_name, apply_date, cert_no, cert_name, cert_date, approver_id, approve_date, approve_result, state)
VALUES ('715ef51eca9911ea9fa9000c29ee2ecd', 1, 'f44d0cc6ca9811ea9fa9000c29ee2ecd', 'user', '2020-07-20 14:57:46', 'msengineer-1234', '中级工程师', '2020-07-19 16:00:00', 'admin', NULL, 0, 1);

INSERT INTO hr.certificate_flow (id, type, pre_id, user_name, apply_date, cert_no, cert_name, cert_date, approver_id, approve_date, approve_result, state)
VALUES ('7d24a036ca9911ea9fa9000c29ee2ecd', 0, NULL, 'user', '2020-07-20 14:58:06', 'mieconmy-12345', '中级经济师', '2020-07-19 16:00:00', 'admin', NULL, 1, 1);


INSERT INTO hr.employee_certificate (id, `user`, cert_no, cert_name, cert_date, notes)
VALUES ('389447f8c9a711ea9fa9000c29ee2ecd', 'admin', 'hsengineer-1234', '高级工程师', '2020-07-18 16:00:00', NULL);

INSERT INTO hr.employee_certificate (id, `user`, cert_no, cert_name, cert_date, notes)
VALUES ('f1bf05cfca9811ea9fa9000c29ee2ecd', 'admin', 'hseconomy-1234', '高级经济师', '2020-07-19 16:00:00', NULL);

INSERT INTO hr.employee_certificate (id, `user`, cert_no, cert_name, cert_date, notes)
VALUES ('f44d0cc6ca9811ea9fa9000c29ee2ecd', 'user', 'msengineer-1234', '中级工程师', '2020-07-19 16:00:00', NULL);

INSERT INTO hr.employee_certificate (id, `user`, cert_no, cert_name, cert_date, notes)
VALUES ('ffbed786c96b11ea9fa9000c29ee2ecd', 'admin', 'hskuaiji-1234', '高级会计师', '2020-07-19 16:00:00', NULL);


INSERT INTO hr.menu (id, sup_id, levels, name, icon, path, sort, rights)
VALUES ('0a52a854c4b711eaab09000c29ee2ecd', 'a23662b7c4b011eaab09000c29ee2ecd', 2, '个人假期查询', 'el-icon-basketball', '/user_hoiday_info', 501, '/user');

INSERT INTO hr.menu (id, sup_id, levels, name, icon, path, sort, rights)
VALUES ('0a543b11c4b711eaab09000c29ee2ecd', 'a23662b7c4b011eaab09000c29ee2ecd', 2, '个人请假明细', 'el-icon-football', '/user_holiday_flow_info', 502, '/user');

INSERT INTO hr.menu (id, sup_id, levels, name, icon, path, sort, rights)
VALUES ('0a561643c4b711eaab09000c29ee2ecd', 'a23662b7c4b011eaab09000c29ee2ecd', 2, '全行假期查询', 'el-icon-s-grid', '/alluser_holiday_info', 503, '/admin');

INSERT INTO hr.menu (id, sup_id, levels, name, icon, path, sort, rights)
VALUES ('0a56bae5c4b711eaab09000c29ee2ecd', 'a23662b7c4b011eaab09000c29ee2ecd', 2, '请假申请', 'el-icon-document-add', '/holiday_apply', 504, '/user');

INSERT INTO hr.menu (id, sup_id, levels, name, icon, path, sort, rights)
VALUES ('0a576439c4b711eaab09000c29ee2ecd', 'a23662b7c4b011eaab09000c29ee2ecd', 2, '请假待审批流程', 'el-icon-document-add', '/pending_holiday_flow', 505, '/admin');

INSERT INTO hr.menu (id, sup_id, levels, name, icon, path, sort, rights)
VALUES ('29689382c4b411eaab09000c29ee2ecd', 'a23474bbc4b011eaab09000c29ee2ecd', 2, '薪酬级别设置', 'el-icon-s-operation', '/salary_set', 301, '/admin');

INSERT INTO hr.menu (id, sup_id, levels, name, icon, path, sort, rights)
VALUES ('29695363c4b411eaab09000c29ee2ecd', 'a23474bbc4b011eaab09000c29ee2ecd', 2, '员工薪酬管理', 'el-icon-s-help', '/user_salary_mng', 302, '/admin');

INSERT INTO hr.menu (id, sup_id, levels, name, icon, path, sort, rights)
VALUES ('29698af5c4b411eaab09000c29ee2ecd', 'a23474bbc4b011eaab09000c29ee2ecd', 2, '个人薪酬查询', 'el-icon-help', '/user_salary_info', 303, '/user');

INSERT INTO hr.menu (id, sup_id, levels, name, icon, path, sort, rights)
VALUES ('3a42597dc4b711eaab09000c29ee2ecd', 'a236f8a1c4b011eaab09000c29ee2ecd', 2, '修改密码', 'el-icon-more-outline', '/change_password', 602, '/user');

INSERT INTO hr.menu (id, sup_id, levels, name, icon, path, sort, rights)
VALUES ('3d902f7dc4b111eaab09000c29ee2ecd', '8f975e11c4b011eaab09000c29ee2ecd', 2, '机构管理', 'el-icon-s-home', '/unit_mng', 102, '/admin');

INSERT INTO hr.menu (id, sup_id, levels, name, icon, path, sort, rights)
VALUES ('8f975e11c4b011eaab09000c29ee2ecd', NULL, 1, '用户管理', 'el-icon-user-solid', NULL, 1, NULL);

INSERT INTO hr.menu (id, sup_id, levels, name, icon, path, sort, rights)
VALUES ('a232f9ddc4b011eaab09000c29ee2ecd', NULL, 1, '岗位管理', 'el-icon-s-fold', NULL, 2, NULL);

INSERT INTO hr.menu (id, sup_id, levels, name, icon, path, sort, rights)
VALUES ('a23474bbc4b011eaab09000c29ee2ecd', NULL, 1, '薪酬管理', 'el-icon-s-help', NULL, 3, NULL);

INSERT INTO hr.menu (id, sup_id, levels, name, icon, path, sort, rights)
VALUES ('a2360c0bc4b011eaab09000c29ee2ecd', NULL, 1, '证书管理', 'el-icon-tickets', NULL, 4, NULL);

INSERT INTO hr.menu (id, sup_id, levels, name, icon, path, sort, rights)
VALUES ('a23662b7c4b011eaab09000c29ee2ecd', NULL, 1, '请休假管理', 'el-icon-document-add', NULL, 5, NULL);

INSERT INTO hr.menu (id, sup_id, levels, name, icon, path, sort, rights)
VALUES ('a236f8a1c4b011eaab09000c29ee2ecd', NULL, 1, '设置中心', 'el-icon-s-tools', NULL, 6, NULL);

INSERT INTO hr.menu (id, sup_id, levels, name, icon, path, sort, rights)
VALUES ('b61273acc4b711eaab09000c29ee2ecd', 'a236f8a1c4b011eaab09000c29ee2ecd', 2, '个人基本信息', 'el-icon-s-tools', '/user_info', 601, '/user');

INSERT INTO hr.menu (id, sup_id, levels, name, icon, path, sort, rights)
VALUES ('cbeaf4cfc4b411eaab09000c29ee2ecd', 'a2360c0bc4b011eaab09000c29ee2ecd', 2, '个人证书管理', 'el-icon-tickets', '/user_cert_mng', 401, '/user');

INSERT INTO hr.menu (id, sup_id, levels, name, icon, path, sort, rights)
VALUES ('cbebbc38c4b411eaab09000c29ee2ecd', 'a2360c0bc4b011eaab09000c29ee2ecd', 2, '全行证书管理', 'el-icon-document-copy', '/alluser_cert_mng', 402, '/admin');

INSERT INTO hr.menu (id, sup_id, levels, name, icon, path, sort, rights)
VALUES ('cbec48c3c4b411eaab09000c29ee2ecd', 'a2360c0bc4b011eaab09000c29ee2ecd', 2, '已发送的证书申请', 'el-icon-football', '/applied_cert_flow', 403, '/user');

INSERT INTO hr.menu (id, sup_id, levels, name, icon, path, sort, rights)
VALUES ('cbecf3b3c4b411eaab09000c29ee2ecd', 'a2360c0bc4b011eaab09000c29ee2ecd', 2, '证书待审批流程', 'el-icon-coordinate', '/pending_cert_flow', 404, '/admin');

INSERT INTO hr.menu (id, sup_id, levels, name, icon, path, sort, rights)
VALUES ('e6108b01c4b311eaab09000c29ee2ecd', 'a232f9ddc4b011eaab09000c29ee2ecd', 2, '岗位设置', 'el-icon-s-fold', '/post_set', 201, '/admin');

INSERT INTO hr.menu (id, sup_id, levels, name, icon, path, sort, rights)
VALUES ('fef29da6c4b011eaab09000c29ee2ecd', '8f975e11c4b011eaab09000c29ee2ecd', 2, '员工基本信息管理', 'el-icon-user-solid', '/user_mng', 101, '/admin');


INSERT INTO hr.org_post (id, name, notes)
VALUES ('1d5b2929c7eb11ea9fa9000c29ee2ecd', '副总经理', '这是副总经理');

INSERT INTO hr.org_post (id, name, notes)
VALUES ('2ac64828c7eb11ea9fa9000c29ee2ecd', '总经理助理', '这是总经理助理');

INSERT INTO hr.org_post (id, name, notes)
VALUES ('9b47b94dc7e711ea9fa9000c29ee2ecd', '总经理', '这是总经理');

INSERT INTO hr.org_post (id, name, notes)
VALUES ('a167d3bac65711eaab09000c29ee2ecd', '办事员', '这是办事员');


INSERT INTO hr.org_unit (id, name, address, phone, manager)
VALUES ('25cebf82c6ab11eaab09000c29ee2ecd', '合规与法律事务部', '北京路30号', '6656991', 'admin');

INSERT INTO hr.org_unit (id, name, address, phone, manager)
VALUES ('2ba0a77fc6a511eaab09000c29ee2ecd', '零售业务部', '中科创新广场', '3623215', 'admin');

INSERT INTO hr.org_unit (id, name, address, phone, manager)
VALUES ('438b7592c5e011eaab09000c29ee2ecd', '信息科技部', '北京路30号', '6326972', 'user');

INSERT INTO hr.org_unit (id, name, address, phone, manager)
VALUES ('4ad2ff2dc78711ea9fa9000c29ee2ecd', '稽核审计部', '北京路', '1234', 'admin');

INSERT INTO hr.org_unit (id, name, address, phone, manager)
VALUES ('5c08a57fc6ad11eaab09000c29ee2ecd', '公司业务部', '北京路uu', '337722', 'admin');

INSERT INTO hr.org_unit (id, name, address, phone, manager)
VALUES ('6e18ff03c5e211eaab09000c29ee2ecd', '网络金融部', '北京路30号', '6326972', 'xiaokang');

INSERT INTO hr.org_unit (id, name, address, phone, manager)
VALUES ('7430461cc5e211eaab09000c29ee2ecd', '人力资源部', '北京路30号', '6326972', 'xiaokang');


INSERT INTO hr.role (id, name, nameZh)
VALUES ('f410de25c38d11eaab09000c29ee2ecd', 'ROLE_admin', '系统管理员');

INSERT INTO hr.role (id, name, nameZh)
VALUES ('f412c01ac38d11eaab09000c29ee2ecd', 'ROLE_user', '用户');


INSERT INTO hr.salary (id, salary, notes)
VALUES ('0dfc2c70c90811ea9fa9000c29ee2ecd', 123456.78, '总经理工资');

INSERT INTO hr.salary (id, salary, notes)
VALUES ('588b8a17c90f11ea9fa9000c29ee2ecd', 12345.67, '副总经理工资');

INSERT INTO hr.salary (id, salary, notes)
VALUES ('622ebd21c90f11ea9fa9000c29ee2ecd', 4321.12, '总经理助理工资');

INSERT INTO hr.salary (id, salary, notes)
VALUES ('9cc4161cc8a811ea9fa9000c29ee2ecd', 345.45, '办事员工资');

INSERT INTO hr.salary (id, salary, notes)
VALUES ('ef27d45eca9b11ea9fa9000c29ee2ecd', 1234.56, '经理工资');


INSERT INTO hr.`user` (id, username, password, enabled, locked)
VALUES ('1dfbca02c5c911eaab09000c29ee2ecd', 'xiaokang', '$2a$10$WotpVWHe68MoR9nKafgLLO.xF89OeUvyow0g7sETuuwFsV/NoRwNe', 1, 0);

INSERT INTO hr.`user` (id, username, password, enabled, locked)
VALUES ('9b3d0648c38d11eaab09000c29ee2ecd', 'admin', '$2a$10$y1XR1/hHWE9JPtAVnOEupedyeSuWBjeS/oSqp9TdJzfeMMrRgOgqu', 1, 0);

INSERT INTO hr.`user` (id, username, password, enabled, locked)
VALUES ('c863dde2c38d11eaab09000c29ee2ecd', 'user', '$2a$10$xbheuUbXzfpU5CWwWek2Je6N8MAuwKiElm315Fgi9d5NmLaVobyOu', 1, 0);

INSERT INTO hr.user_info (username, phone, address, email, unit, `level`, position, salary, photo)
VALUES ('admin', '1231', '北京路30号1', '23421@qq.com1', '438b7592c5e011eaab09000c29ee2ecd', NULL, 'a167d3bac65711eaab09000c29ee2ecd', '588b8a17c90f11ea9fa9000c29ee2ecd', NULL);

INSERT INTO hr.user_info (username, phone, address, email, unit, `level`, position, salary, photo)
VALUES ('xiaokang', 'phone', 'address', 'email', '438b7592c5e011eaab09000c29ee2ecd', NULL, 'a167d3bac65711eaab09000c29ee2ecd', '622ebd21c90f11ea9fa9000c29ee2ecd', NULL);

INSERT INTO hr.user_info (username, phone, address, email, unit, `level`, position, salary, photo)
VALUES ('user', '12342314', '江门', '345@qq.com', '25cebf82c6ab11eaab09000c29ee2ecd', NULL, 'a167d3bac65711eaab09000c29ee2ecd', '9cc4161cc8a811ea9fa9000c29ee2ecd', NULL);


INSERT INTO hr.user_role (id, uid, rid)
VALUES ('1dfd15d7c5c911eaab09000c29ee2ecd', '1dfbca02c5c911eaab09000c29ee2ecd', 'f412c01ac38d11eaab09000c29ee2ecd');

INSERT INTO hr.user_role (id, uid, rid)
VALUES ('34613504c38e11eaab09000c29ee2ecd', '9b3d0648c38d11eaab09000c29ee2ecd', 'f410de25c38d11eaab09000c29ee2ecd');

INSERT INTO hr.user_role (id, uid, rid)
VALUES ('464863ecc77a11ea9fa9000c29ee2ecd', '4646eab6c77a11ea9fa9000c29ee2ecd', 'f412c01ac38d11eaab09000c29ee2ecd');

INSERT INTO hr.user_role (id, uid, rid)
VALUES ('49911376c38e11eaab09000c29ee2ecd', 'c863dde2c38d11eaab09000c29ee2ecd', 'f412c01ac38d11eaab09000c29ee2ecd');

INSERT INTO hr.user_role (id, uid, rid)
VALUES ('aea2ef93c5c811eaab09000c29ee2ecd', 'aea16d0dc5c811eaab09000c29ee2ecd', 'f412c01ac38d11eaab09000c29ee2ecd');

INSERT INTO hr.user_role (id, uid, rid)
VALUES ('bb9c2169c77a11ea9fa9000c29ee2ecd', 'bb9a542fc77a11ea9fa9000c29ee2ecd', 'f412c01ac38d11eaab09000c29ee2ecd');

INSERT INTO hr.user_role (id, uid, rid)
VALUES ('befa8284c38e11eaab09000c29ee2ecd', '9b3d0648c38d11eaab09000c29ee2ecd', 'f412c01ac38d11eaab09000c29ee2ecd');

INSERT INTO hr.user_role (id, uid, rid)
VALUES ('ee4d127dc77a11ea9fa9000c29ee2ecd', 'ee4bd67ac77a11ea9fa9000c29ee2ecd', 'f412c01ac38d11eaab09000c29ee2ecd');

INSERT INTO hr.user_role (id, uid, rid)
VALUES ('f2c8b270c5c811eaab09000c29ee2ecd', 'f2c733b0c5c811eaab09000c29ee2ecd', 'f412c01ac38d11eaab09000c29ee2ecd');


