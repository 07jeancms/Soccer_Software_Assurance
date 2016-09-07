function val = pearson(pArray1, pArray2)
    val = 0;
    result = 0;
    sum = 0; 
    len1 = length(pArray1);
    len2 = length(pArray2);
    
    % Here we sum the values in the first array, called pArray1 then it
    % will be stored into sum variable
    for actualElement = 1:len1
        sum = sum + pArray1(actualElement);
    end
    
    % Average of the first array(pArray1)
    averageXarray = sum/len1;
    sum = 0;
    
    % Here we sum the values in the second array, called pArray2 then it
    % will be stored into sum variable
    for actualElement = 1:len2
        sum = sum + pArray2(actualElement);
    end
    
    % Average of the second array(pArray2)
    averageYarray = sum/len2;
    pxy = 0;
    sx2 = 0;
    sy2 = 0;
    
    for actualElement = 1:len1
        pxy = pxy + (pArray1(actualElement) - averageXarray) * (pArray2(actualElement) - averageYarray);
        sx2 = sx2 + (pArray1(actualElement) - averageXarray) * (pArray1(actualElement) - averageXarray);
        sy2 = sy2 + (pArray2(actualElement) - averageYarray) * (pArray2(actualElement) - averageYarray);
    end
    
    result = pxy/sqrt(sx2*sy2);
    val = [1 result ; result 1];
end

    
