INSERT INTO employee( employee_id, employee_name, age)
  VALUES(1, '山田太郎', 30);
INSERT INTO employee( employee_id, employee_name, age)
  VALUES(2, '田中花子', 20);

/* アドミン権限 */
INSERT INTO m_user(user_id, password, user_name, birthday, age, marriage, role)
  VALUES('yamada@xxx.co.jp', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', '山田太郎', '1990-01-01', 28, false, 'ROLE_ADMIN');
 /* 一般権限*/
 INSERT INTO m_user(user_id, password, user_name, birthday, age, marriage, role)
  VALUES('tanaka@xxx.co.jp', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', '田中達也', '1986-11-05', 31, false, 'ROLE_GENERAL');
