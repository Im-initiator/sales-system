package com.leminhtien.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import com.leminhtien.entity.CategoryGenderEntity;
import com.leminhtien.repository.CategoryGenderRepository;
import com.leminhtien.utils.FileUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leminhtien.dto.ProductDTO;
import com.leminhtien.entity.CategoryEntity;
import com.leminhtien.entity.ProductEntity;
import com.leminhtien.entity.SizeEntity;
import com.leminhtien.repository.CategoryRepository;
import com.leminhtien.repository.ProductRepository;
import com.leminhtien.repository.SizeRepository;
import com.leminhtien.service.IProductService;
import org.springframework.transaction.TransactionSystemException;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private CategoryGenderRepository categoryGenderRepository;

    @Override
    public List<ProductDTO> findAll() {
        List<ProductEntity> list = productRepository.findAll();
        List<ProductDTO> result = new ArrayList<ProductDTO>();
        for (ProductEntity product : list) {
            if (product != null) {
                result.add(mapper.map(product, ProductDTO.class));
            }
        }
        return result;
    }

    @Override
    public List<ProductDTO> findAll(Pageable pageable) {
        List<ProductEntity> list = productRepository.findAllByOrderByName(pageable);
        List<ProductDTO> result = new ArrayList<ProductDTO>();
        for (ProductEntity product : list) {
            if (product != null) {
                result.add(mapper.map(product, ProductDTO.class));
            }
        }
        return result;
    }

    @Override
    public ProductDTO findById(Long id) {
        ProductEntity product = productRepository.findOne(id);
        if (product != null) {
            ProductDTO productDTO = mapper.map(product, ProductDTO.class);
            productDTO.setCategoryCode(productDTO.getCategory().getCode());
            productDTO.setGenderCode(product.getCategoryGender().getCode());
            List<String> sizes = new ArrayList<String>();
            List<SizeEntity> sizeProduct = product.getSizes();
            for (SizeEntity size : sizeProduct) {
                if (size != null) {
                    sizes.add(size.getName());
                }
            }
            productDTO.setSizes(sizes);
            return productDTO;

        }
        return null;
    }

    @Override
    @Transactional
    public ProductDTO save(ProductDTO productDTO) {
        try {
            if (productDTO != null) {
                CategoryEntity categoryEntity = categoryRepository.findOneByCode(productDTO.getCategoryCode());
                CategoryGenderEntity categoryGenderEntity = categoryGenderRepository.findOneByCode(productDTO.getGenderCode());
                if (productDTO.getId() != null) {
                    ProductDTO productOld = this.findById(productDTO.getId());
                    if (productDTO.getFileImage().getSize() != 0) {
                        if (FileUtils.deleteFile(productOld.getImg(), servletContext)) {
                            try {
                                String path = FileUtils.saveImage(productDTO.getFileImage(), servletContext);
                                productDTO.setImg(path);
                            } catch (IOException e) {
                                e.printStackTrace();
                                return null;
                            }
                        } else {
                            return null;
                        }
                    }else{
                        productDTO.setImg(productOld.getImg());
                    }
                    productDTO.setPrize(productOld.getPrize());
                    productDTO.setSellNumber(productOld.getSellNumber());
                } else {
                    productDTO.setSellNumber(0);
                    productDTO.setPrize(0f);
                    String filePath = FileUtils.saveImage(productDTO.getFileImage(), servletContext);
                    if (filePath == null) {
                        return null;
                    }
                    productDTO.setImg(filePath);
                }
                try {
                    ProductEntity productEntity = mapper.map(productDTO, ProductEntity.class);
                    List<SizeEntity> sizes = new ArrayList<SizeEntity>();
                    for (String size : productDTO.getSizes()) {
                        SizeEntity sizeEntity = sizeRepository.findOneByName(size);
                        if (sizeEntity != null) {
                            sizes.add(sizeEntity);
                        }
                    }
                    productEntity.setSizes(sizes);

                    if (categoryEntity.getId() != null) {
                        productEntity.setCategory(categoryEntity);
                    }else {
                        throw new NullPointerException();
                    }

                    if(categoryGenderEntity.getId()!= null){
                        productEntity.setCategoryGender(categoryGenderEntity);
                    }else {
                        throw new NullPointerException();
                    }
                    productEntity = productRepository.save(productEntity);
                    return mapper.map(productEntity, ProductDTO.class);
                } catch (NullPointerException | DataAccessException | TransactionSystemException e) {
                    e.printStackTrace();
                    FileUtils.deleteFile(productDTO.getImg(), servletContext);
                }
            }
        } catch (Exception e) {
            return null;
        }
    return null;

    }

    @Override
    @Transactional
    public boolean delete(Long[] ids) {
        boolean flag = true;
        try {
            for (Long id : ids) {
                try {
                    productRepository.delete(id);
                } catch (Exception e) {
                    e.printStackTrace();
                    flag = false;
                }
            }
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public long count() {
        return productRepository.count();
    }

    @Override
    public long countByName(String name) {
        return productRepository.countByNameContaining(name);
    }

    @Override
    public List<ProductDTO> searchByName(String name, Pageable pageable) {
        List<ProductEntity> list = productRepository.findAllByNameContaining(name,pageable);
        List<ProductDTO> result = new ArrayList<>();
        for(ProductEntity item : list){
            result.add(mapper.map(item,ProductDTO.class));
        }
        return result;

    }


}
