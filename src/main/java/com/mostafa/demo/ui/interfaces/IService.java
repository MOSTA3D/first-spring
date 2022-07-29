package com.mostafa.demo.ui.interfaces;

import java.util.List;
import java.util.Optional;

public interface IService <ReqType, RetType, Entity>{
	public List<Entity> getAll();
	public Optional<Entity> getOne(Long id);
	public Entity create(ReqType data);
	public Entity update(Long id, ReqType data);
	public void delete(Long id);
}
