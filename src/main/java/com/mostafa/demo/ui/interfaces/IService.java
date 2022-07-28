package com.mostafa.demo.ui.interfaces;

import java.util.List;

public interface IService <ReqType, RetType, Entity>{
	public List<Entity> getAll();
	public Entity getOne(Long id);
	public Entity create(ReqType data);
	public Entity update(Long id, ReqType data);
	public void delete(Long id);
}
