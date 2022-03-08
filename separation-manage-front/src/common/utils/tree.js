//第一种方法格式化成树形结构(递归)
export function changeListToTreeOne(itemList, itemId, itemFid) {
  //先将列表数据分成两类，第一类就是就是pid==0的，第二类就是pid！==0的,
  //因为pid==0肯定是父亲，其他的不管是谁的儿子，肯定是儿子
  let parents = itemList.filter((p) => p[itemFid] === 0);
  let childrens = itemList.filter((c) => c[itemFid] !== 0);
  generateTree(parents, childrens);
  function generateTree(parents, childrens) {
    parents.map((p) => {
      childrens.map((c, idx) => {
        if (p[itemId] === c[itemFid]) {
          //拷贝一份剔除当前的这一项所有儿子的数据，就是其他的所有儿子，不管是谁的儿子
          let _c = JSON.parse(JSON.stringify(childrens));
          _c.splice(idx, 1);
          //到了这一步，就可以理解为这一项已经找到父亲了，就开始找这一项的儿子
          generateTree([c], _c);
          if (p.children) {
            p.children.push(c);
          } else {
            p.children = [c];
          }
        }
      });
    });
  }
  return parents;
}

// 第二种方法格式化成树形结构
export function changeListToTreeTwo(itemList, itemId, itemFid) {
  let cloneData = JSON.parse(JSON.stringify(itemList));
  return cloneData.filter((father) => {
    let branchArr = cloneData.filter((child) => father[itemId] === child[itemFid]);
    branchArr.length > 0 ? (father.children = branchArr) : "";
    return father[itemFid]=== 0; // 如果第一层不是parentId=0，请自行修改
  });
}
