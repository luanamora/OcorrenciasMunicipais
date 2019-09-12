using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using WebApi.Models;

namespace WebApi.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class AreaAtuacaosController : ControllerBase
    {
        private readonly ocorrenciasdbContext _context;

        public AreaAtuacaosController(ocorrenciasdbContext context)
        {
            _context = context;
        }

        // GET: api/AreaAtuacaos
        [HttpGet]
        public IEnumerable<AreaAtuacao> GetAreaAtuacao()
        {
            return _context.AreaAtuacao;
        }

        // GET: api/AreaAtuacaos/5
        [HttpGet("{id}")]
        public async Task<IActionResult> GetAreaAtuacao([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var areaAtuacao = await _context.AreaAtuacao.FindAsync(id);

            if (areaAtuacao == null)
            {
                return NotFound();
            }

            return Ok(areaAtuacao);
        }

        // PUT: api/AreaAtuacaos/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutAreaAtuacao([FromRoute] int id, [FromBody] AreaAtuacao areaAtuacao)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != areaAtuacao.CdAreaatuacao)
            {
                return BadRequest();
            }

            _context.Entry(areaAtuacao).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!AreaAtuacaoExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtAction("GetAreaAtuacao", new { id = areaAtuacao.CdAreaatuacao}, areaAtuacao);
        }

        // POST: api/AreaAtuacaos
        [HttpPost]
        public async Task<IActionResult> PostAreaAtuacao([FromBody] AreaAtuacao areaAtuacao)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            _context.AreaAtuacao.Add(areaAtuacao);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetAreaAtuacao", new { id = areaAtuacao.CdAreaatuacao }, areaAtuacao);
        }

        // DELETE: api/AreaAtuacaos/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteAreaAtuacao([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var areaAtuacao = await _context.AreaAtuacao.FindAsync(id);
            if (areaAtuacao == null)
            {
                return NotFound();
            }

            _context.AreaAtuacao.Remove(areaAtuacao);
            await _context.SaveChangesAsync();

            return Ok(areaAtuacao);
        }

        private bool AreaAtuacaoExists(int id)
        {
            return _context.AreaAtuacao.Any(e => e.CdAreaatuacao == id);
        }
    }
}